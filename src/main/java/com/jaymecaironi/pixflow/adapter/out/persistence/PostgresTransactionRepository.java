package com.jaymecaironi.pixflow.adapter.out.persistence;

import com.jaymecaironi.pixflow.application.port.out.TransactionRepository;
import com.jaymecaironi.pixflow.domain.model.Transaction;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresTransactionRepository implements TransactionRepository {
    
    private final SpringDataTransactionRepository springData;

    public PostgresTransactionRepository(SpringDataTransactionRepository springData){
        this.springData = springData;
    }

    @Override
    public Transaction save(Transaction tx){
        return springData.save(TransactionJpaEntity.fromDomanin(tx)).toDomain();
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return springData.findById(id).map(TransactionJpaEntity::toDomain);
    }
}
