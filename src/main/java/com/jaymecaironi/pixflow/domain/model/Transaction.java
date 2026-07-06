package com.jaymecaironi.pixflow.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Transaction(
    UUID id,
    String originKey,
    String destinationKey,
    BigDecimal amount,
    TransactionStatus status,
    Instant createdAt
) {
    public Transaction {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo.");
        }
        if (originKey == null || originKey.isBlank() || 
        destinationKey == null || destinationKey.isBlank()) {
            throw new IllegalArgumentException("Chaves PIX são obrigatórias.");
        }
        if (originKey.equals(destinationKey)) {
            throw new IllegalArgumentException("Origem e destino não podem ser iguais.");
        }
    }

    public static Transaction create(String originKey, String destinationKey, BigDecimal amount) {
        return new Transaction(UUID.randomUUID(), originKey, destinationKey, amount,
            TransactionStatus.PENDING, Instant.now());
    }

    public Transaction withStatus(TransactionStatus newStatus) {
        return new Transaction(id, originKey, destinationKey, amount, newStatus, createdAt);
    }
}
