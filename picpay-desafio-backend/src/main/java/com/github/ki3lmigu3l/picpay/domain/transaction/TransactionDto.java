package com.github.ki3lmigu3l.picpay.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDto(
        BigDecimal amount,
        UUID userPayer,
        UUID userPayee,
        LocalDateTime transactionTime

        ) {
}
