package com.jaymecaironi.pixflow.adapter.out.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "fraud_alerts")
public record FraudAlertDocument(
        @Id String id,
        UUID transactionId,
        BigDecimal amount,
        String reason,
        Instant detectedAt
) {}
