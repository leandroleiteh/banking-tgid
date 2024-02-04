package com.lebanking.api.application.dtos.output;

import com.lebanking.api.domain.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionOutputDTO(

        UUID id,

        LocalDateTime timestamp,

        BigDecimal amount,

        BigDecimal totalAmount,

        TransactionType transactionType

) {
}
