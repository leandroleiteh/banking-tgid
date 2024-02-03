package com.lebanking.api.domain.model.fee;

import java.math.BigDecimal;

public interface FeeStrategy {
    BigDecimal calculateFee(BigDecimal amount);
}
