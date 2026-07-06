package com.jaymecaironi.pixflow.application.port.out;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction tx);
    Optional<Transaction> findById(UUID id);
}
