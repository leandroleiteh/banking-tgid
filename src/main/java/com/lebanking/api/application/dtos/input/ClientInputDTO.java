package com.lebanking.api.application.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.Date;

public record ClientInputDTO(

        @NotBlank(message = "O Nome não pode ser nulo")
        String name,

        @NotBlank(message = "O Cpf não pode ser nulo")
        @CPF(message = "Formato válido: 12345678909")
        String cpf,

        @NotNull(message = "Preencha sua data de nascimento")
        Date birthDate,


        @NotBlank(message = "O telefone não pode ser nulo. Formato válido: 11947165215")
        String tel,

        @Email(message = "insira no formato válido: joao.silva@example.com")
        @NotBlank(message = "O email não pode ser nulo")
        String mail,

        BigDecimal balance
) {
}
