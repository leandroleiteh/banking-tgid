package com.lebanking.api.application.dtos.output;

import java.math.BigDecimal;

public record FeeOutputDTO(

        BigDecimal withdrawalFeeRate,

        BigDecimal depositFeeRate

) {
}
