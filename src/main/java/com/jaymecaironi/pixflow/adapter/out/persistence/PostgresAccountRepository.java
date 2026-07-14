package com.jaymecaironi.pixflow.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jaymecaironi.pixflow.application.port.out.AccountRepository;

import jakarta.transaction.Transactional;

@Component
public class PostgresAccountRepository implements AccountRepository {
    
    private final SpringDataAccountRepositoy springData;

    public PostgresAccountRepository(SpringDataAccountRepositoy springData) {
        this.springData = springData;
    }

    @Override
    @Transactional
    public boolean debit(String pixKey, BigDecimal amount) {
        return springData.debitIfSufficient(pixKey, amount) == 1;
    }

    @Override
    public Optional<BigDecimal> findBalance(String pixKey) {
        return springData.findBalanceByPixKey(pixKey);
    }
}
