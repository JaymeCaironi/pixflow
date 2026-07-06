package com.jaymecaironi.pixflow.application;

import com.jaymecaironi.pixflow.application.port.in.CreateTransactionUseCase;
import com.jaymecaironi.pixflow.application.port.out.TransactionEventPublisher;
import com.jaymecaironi.pixflow.application.port.out.TransactionRepository;
import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.math.BigDecimal;

public class CreateTransactionService implements CreateTransactionUseCase {
    
    private final TransactionRepository repository;
    private final TransactionEventPublisher publisher;

    public CreateTransactionService(TransactionRepository repository,
                                    TransactionEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public Transaction create(String originKey, String destinationKey, BigDecimal amount) {
        Transaction tx = Transaction.create(originKey, destinationKey, amount);
        Transaction saved = repository.save(tx);
        publisher.publishCreated(saved);
        return saved;
    }
}
