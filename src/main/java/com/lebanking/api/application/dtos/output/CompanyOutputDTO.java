package com.lebanking.api.application.dtos.output;

import java.math.BigDecimal;
import java.util.UUID;

public record CompanyOutputDTO(

        UUID id,

        String name,

        String cnpj,

        String contact,

        BigDecimal balance,

        FeeOutputDTO feeOutputDTO
) {
}
