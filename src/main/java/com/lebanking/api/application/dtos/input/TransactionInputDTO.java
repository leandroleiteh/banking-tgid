package com.lebanking.api.application.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record TransactionInputDTO(

        @NotBlank(message = "O Cnpj não pode ser nulo")
        @CNPJ(message = "Formato válido: 12345678000190")
        String cnpj,

        @NotBlank(message = "O Cpf não pode ser nulo")
        @CPF(message = "Formato válido: 12345678909")
        String cpf,

        @NotNull(message = "Insira o valor ")
        BigDecimal amount
) {
}
