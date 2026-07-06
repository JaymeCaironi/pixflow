package com.jaymecaironi.pixflow.adapter.out.persistence;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import com.jaymecaironi.pixflow.domain.model.TransactionStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String originKey;

    @Column(nullable = false)
    private String destinationKey;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    protected TransactionJpaEntity() {}

    public static TransactionJpaEntity fromDomanin(Transaction tx) {
        var e = new TransactionJpaEntity();
        e.id = tx.id();
        e.originKey = tx.originKey();
        e.destinationKey = tx.destinationKey();
        e.amount = tx.amount();
        e.status = tx.status();
        e.createdAt = tx.createdAt();
        return e;
    }

    public Transaction toDomain() {
        return new Transaction(id, originKey, destinationKey, amount, status, createdAt);
    }
}
