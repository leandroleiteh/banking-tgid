package com.lebanking.api.application.dtos;

import java.math.BigDecimal;
import java.util.Date;

public record ClientInputDTO(

        String name,

        String cpf,

        Date birthDate,

        String tel,

        String mail,

        BigDecimal balance
) {
}
