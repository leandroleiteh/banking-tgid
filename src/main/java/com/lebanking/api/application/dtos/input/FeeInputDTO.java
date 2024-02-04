package com.lebanking.api.application.dtos.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Table(name= "Taxas")
public record FeeInputDTO(

        @NotNull(message = "Insira a taxa do saque")
        @NotEmpty(message = "insira a taxa do saque")
        BigDecimal withdrawalFeeRate,

        @NotNull(message = "Insira a taxa do deposito")
        @NotEmpty(message = "insira a taxa do deposito")
        BigDecimal depositFeeRate

) {
}
