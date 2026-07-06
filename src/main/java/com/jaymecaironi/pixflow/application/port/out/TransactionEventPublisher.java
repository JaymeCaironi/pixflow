package com.jaymecaironi.pixflow.application.port.out;

import com.jaymecaironi.pixflow.domain.model.Transaction;

public interface TransactionEventPublisher {
    void publishCreated(Transaction tx);
}
