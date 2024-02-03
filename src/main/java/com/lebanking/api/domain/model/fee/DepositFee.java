package com.lebanking.api.domain.model.fee;

import java.math.BigDecimal;

public class DepositFee implements FeeStrategy {

    private static final BigDecimal DEPOSIT_FEE_RATE = BigDecimal.valueOf(0.01);

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return amount.multiply(DEPOSIT_FEE_RATE);
    }
}
