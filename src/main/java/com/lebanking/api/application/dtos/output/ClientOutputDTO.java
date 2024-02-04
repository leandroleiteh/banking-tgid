package com.lebanking.api.application.dtos.output;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record ClientOutputDTO(

         UUID id,

         String name,

         String cpf,

         Date birthDate,

         String tel,

         String mail,

         BigDecimal balance

) {
}
