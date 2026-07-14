package com.jaymecaironi.pixflow.application;

import com.jaymecaironi.pixflow.application.port.in.CreateTransactionUseCase;
import com.jaymecaironi.pixflow.application.port.out.AccountRepository;
import com.jaymecaironi.pixflow.application.port.out.TransactionEventPublisher;
import com.jaymecaironi.pixflow.application.port.out.TransactionRepository;
import com.jaymecaironi.pixflow.domain.InsufficientBalanceException;
import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.math.BigDecimal;

public class CreateTransactionService implements CreateTransactionUseCase {
    
    private final TransactionRepository repository;
    private final TransactionEventPublisher publisher;
    private final AccountRepository accountRepository;

    public CreateTransactionService(TransactionRepository repository,
                                    TransactionEventPublisher publisher,
                                    AccountRepository accountRepository) {
        this.repository = repository;
        this.publisher = publisher;
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction create(String originKey, String destinationKey, BigDecimal amount) {
        boolean debited = accountRepository.debit(originKey, amount);
        if (!debited) {
            throw new InsufficientBalanceException(originKey);
        }
        Transaction tx = Transaction.create(originKey, destinationKey, amount);
        Transaction saved = repository.save(tx);
        publisher.publishCreated(saved);
        return saved;
    }
}
