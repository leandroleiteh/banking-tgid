package com.lebanking.api.domain.services;

import com.lebanking.api.application.dtos.input.TransactionInputDTO;
import com.lebanking.api.application.dtos.output.TransactionOutputDTO;
import com.lebanking.api.application.email.EmailTransaction;
import com.lebanking.api.domain.model.Client;
import com.lebanking.api.domain.model.Company;
import com.lebanking.api.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {


    TransactionOutputDTO createDeposit(TransactionInputDTO transactionInputDTO);

    TransactionOutputDTO createWithDrawal(TransactionInputDTO transactionInputDTO);

    Client getClientForCpf(String clientCpf);

    Company getCompanyForCnpj(String companyCnpj);

    TransactionOutputDTO outputTransactionAssembler(Transaction transaction);

    TransactionOutputDTO handleWebhookFailure(Transaction transaction);

    Page<TransactionOutputDTO> findAllTransactions(Pageable pageable);

}
