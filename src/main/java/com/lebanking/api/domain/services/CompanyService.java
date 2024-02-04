package com.lebanking.api.domain.services;


import com.lebanking.api.application.dtos.input.CompanyInputDTO;
import com.lebanking.api.application.dtos.output.CompanyOutputDTO;
import com.lebanking.api.domain.model.Company;

import java.math.BigDecimal;
import java.util.UUID;

public interface CompanyService {

    CompanyOutputDTO createCompany(CompanyInputDTO companyInputDTO);

    CompanyOutputDTO outputCompanyAssembler(Company company);

    void existingCnpj(Company company);

    Company searchForCnpjCompany(String cnpj);

    void checkSufficientBalanceForWithdrawal(Company company, BigDecimal totalAmount);
}
