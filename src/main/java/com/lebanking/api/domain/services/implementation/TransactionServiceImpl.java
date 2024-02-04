package com.lebanking.api.domain.services.implementation;

import com.lebanking.api.application.dtos.input.TransactionInputDTO;
import com.lebanking.api.application.dtos.output.TransactionOutputDTO;
import com.lebanking.api.application.email.EmailTransaction;
import com.lebanking.api.application.feign.WebhookSiteClient;
import com.lebanking.api.common.exception.WebhookSiteException;
import com.lebanking.api.domain.model.Client;
import com.lebanking.api.domain.model.Company;
import com.lebanking.api.domain.model.Transaction;
import com.lebanking.api.domain.model.enums.TransactionType;
import com.lebanking.api.domain.services.ClientService;
import com.lebanking.api.domain.services.CompanyService;
import com.lebanking.api.domain.services.TransactionService;
import com.lebanking.api.infrastructure.repositorys.TransactionRepository;
import feign.RetryableException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientService clientService;
    private final CompanyService companyService;
    private final WebhookSiteClient webhookSiteClient;
    private final EmailTransaction emailTransaction;


    public TransactionServiceImpl(TransactionRepository transactionRepository, ClientService clientService, CompanyService companyService, WebhookSiteClient webhookSiteClient, EmailTransaction emailTransaction) {
        this.transactionRepository = transactionRepository;
        this.clientService = clientService;
        this.companyService = companyService;
        this.webhookSiteClient = webhookSiteClient;
        this.emailTransaction = emailTransaction;
    }


    @Override
    public TransactionOutputDTO createDeposit(TransactionInputDTO transactionInputDTO) {
        Client client = clientService.searchForCpfClient(transactionInputDTO.cpf());
        Company company = companyService.searchForCnpjCompany(transactionInputDTO.cnpj());

        BigDecimal depositFee = company.getFee().getDepositFeeRate();
        BigDecimal feeAmount = transactionInputDTO.amount().multiply(depositFee);
        BigDecimal totalAmount = transactionInputDTO.amount().subtract(feeAmount);

        company.setBalance(company.getBalance().add(totalAmount));
        client.setBalance(client.getBalance().add(transactionInputDTO.amount()));

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setCompany(company);
        transaction.setAmount(transactionInputDTO.amount());
        transaction.setTotalAmout(totalAmount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());

        try {
            log.info("Transação de depósito processada com Sucesso: {}", transaction);
            transactionRepository.save(transaction);
            webhookSiteClient.sendCallback(transaction);

            TransactionOutputDTO transactionOutputDTO = outputTransactionAssembler(transaction);
            emailTransaction.sendEmailNewTransactionDeposit(transactionOutputDTO, client.getMail());

            return transactionOutputDTO;
        } catch (RetryableException exception) {
            log.error("Falha no envio do callback para o webhook. ", exception);
            return handleWebhookFailure(transaction);
        }
    }


    @Override
    public TransactionOutputDTO createWithDrawal(TransactionInputDTO transactionInputDTO) {
        Client client = clientService.searchForCpfClient(transactionInputDTO.cpf());
        Company company = companyService.searchForCnpjCompany(transactionInputDTO.cnpj());

        BigDecimal withdrawalFeeRate = company.getFee().getWithdrawalFeeRate();

        BigDecimal withdrawalFee = transactionInputDTO.amount().multiply(withdrawalFeeRate);

        BigDecimal totalAmount = transactionInputDTO.amount().subtract(withdrawalFee);

        companyService.checkSufficientBalanceForWithdrawal(company, totalAmount);

        company.setBalance(company.getBalance().subtract(totalAmount));
        client.setBalance(client.getBalance().add(transactionInputDTO.amount()));

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setCompany(company);
        transaction.setAmount(transactionInputDTO.amount());
        transaction.setTotalAmout(totalAmount);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setTimestamp(LocalDateTime.now());

        try {
            log.info("Transação de saque processada com Sucesso: {}", transaction);
            transactionRepository.save(transaction);
            webhookSiteClient.sendCallback(transaction);

            TransactionOutputDTO transactionOutputDTO = outputTransactionAssembler(transaction);
            emailTransaction.sendEmailNewTransactionDeposit(transactionOutputDTO, client.getMail());

            return transactionOutputDTO;
        } catch (RetryableException exception) {
            log.error("Falha no envio do callback para o webhook. ", exception);
            return handleWebhookFailure(transaction);
        }
    }

    @Override
    public Page<TransactionOutputDTO> findAllTransactions(Pageable pageRequest) {
        Page<Transaction> transactions = transactionRepository.findAll(pageRequest);

        return transactions.map(this::outputTransactionAssembler);
    }


    @Override
    public Client getClientForCpf(String clientCpf) {
        return clientService.searchForCpfClient(clientCpf);
    }

    @Override
    public Company getCompanyForCnpj(String companyCnpj) {
        return companyService.searchForCnpjCompany(companyCnpj);
    }

    @Override
    public TransactionOutputDTO outputTransactionAssembler(Transaction transaction) {
        return new TransactionOutputDTO(
                transaction.getId(),
                transaction.getTimestamp(),
                transaction.getAmount(),
                transaction.getTotalAmout(),
                transaction.getTransactionType());
    }

    @Override
    public TransactionOutputDTO handleWebhookFailure(Transaction transaction) {
        transaction.getClient().setBalance(transaction.getClient().getBalance().subtract(transaction.getAmount()));
        transaction.getCompany().setBalance(transaction.getCompany().getBalance().subtract(transaction.getAmount()));

        log.error("Falha no envio do callback para o webhook. Alterações revertidas.", transaction);

        throw new WebhookSiteException("Falha na transação. As alterações foram revertidas.");
    }

}
