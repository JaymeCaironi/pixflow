package com.jaymecaironi.pixflow.adapter.in.rest.dto;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
    UUID id, String originKey, String destinationKey, BigDecimal amount,
    String status, Instant createdAt) {
    
    public static TransactionResponse from(Transaction tx) {
        return new TransactionResponse(tx.id(), tx.originKey(), tx.destinationKey(), 
        tx.amount(), tx.status().name(), tx.createdAt());
    }
}
