package com.lebanking.api.application.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

public record CompanyInputDTO(

        @NotBlank(message = "O Nome não pode ser nulo")
        String name,

        @NotBlank(message = "O Cnpj não pode ser nulo")
        @CNPJ(message = "Formato válido: 12345678000190")
        String cnpj,

        @NotBlank(message = "O telefone não pode ser nulo. Formato válido: 11947165215")
        String contact,

        BigDecimal balance,

        @NotNull(message = "Preencha as taxas para saque e deposito")
        FeeInputDTO feeInputDTO


) {
}
