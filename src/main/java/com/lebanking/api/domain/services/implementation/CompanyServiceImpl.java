package com.lebanking.api.domain.services.implementation;

import com.lebanking.api.application.dtos.input.CompanyInputDTO;
import com.lebanking.api.application.dtos.output.CompanyOutputDTO;
import com.lebanking.api.application.dtos.output.FeeOutputDTO;
import com.lebanking.api.common.exception.InsufficientFundsException;
import com.lebanking.api.common.exception.ResourceNotFoundException;
import com.lebanking.api.domain.model.Company;
import com.lebanking.api.domain.model.Fee;
import com.lebanking.api.domain.services.CompanyService;
import com.lebanking.api.infrastructure.repositorys.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public CompanyOutputDTO createCompany(CompanyInputDTO companyInputDTO) {

        Company company = new Company(
                companyInputDTO.name(),
                companyInputDTO.cnpj(),
                companyInputDTO.contact(),
                companyInputDTO.balance(),
                new Fee(
                        companyInputDTO.feeInputDTO().withdrawalFeeRate(),
                        companyInputDTO.feeInputDTO().depositFeeRate()));

        existingCnpj(company);

        return outputCompanyAssembler(companyRepository.save(company));

    }

    @Override
    public CompanyOutputDTO outputCompanyAssembler(Company company) {
        return new CompanyOutputDTO(
                company.getId(),
                company.getName(),
                company.getCnpj(),
                company.getContact(),
                company.getBalance(),
                new FeeOutputDTO(
                        company.getFee().getWithdrawalFeeRate(),
                        company.getFee().getDepositFeeRate()));
    }

    @Override
    public void existingCnpj(Company company) {
        boolean existing = companyRepository.findByCnpj(company.getCnpj())
                .stream()
                .anyMatch(e -> !e.equals(company));
        if (existing) throw new ResourceNotFoundException
                ("Não foi possível o cadastro da Empresa " + company.getName().toUpperCase() + ". a mesma já existe.");
    }

    @Override
    public Company searchForCnpjCompany(String cnpj) {
        return companyRepository.findByCnpj(cnpj).orElseThrow(
                () -> new ResourceNotFoundException("Empresa com CNPJ: " + cnpj + " não encontrada!")
        );
    }

    public void checkSufficientBalanceForWithdrawal(Company company, BigDecimal totalAmount) {
        if (company.getBalance().compareTo(totalAmount) < 0) {
              throw new InsufficientFundsException("A Empresa não tem o valor solicidato disponível");
        }
    }

}

