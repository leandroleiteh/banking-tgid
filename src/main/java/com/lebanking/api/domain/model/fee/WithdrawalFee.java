package com.lebanking.api.domain.model.fee;

import java.math.BigDecimal;

public class WithdrawalFee implements FeeStrategy {

    private static final BigDecimal WITHDRAWAL_FEE_RATE = BigDecimal.valueOf(0.02);

    @Override
    public BigDecimal calculateFee(BigDecimal amount) {
        return amount.multiply(WITHDRAWAL_FEE_RATE);
    }
}
