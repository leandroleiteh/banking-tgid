package com.lebanking.api.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Fee {

    @NotNull
    private BigDecimal withdrawalFeeRate;

    @NotNull
    private BigDecimal depositFeeRate;

}
