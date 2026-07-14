package com.jaymecaironi.pixflow.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountJpaEntity {
    
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String pixKey;

    @Column(nullable = false, unique = true)
    private BigDecimal balance;

    protected AccountJpaEntity() {}

    public AccountJpaEntity(UUID id, String pixKey, BigDecimal balance) {
        this.id = id;
        this.pixKey = pixKey;
        this.balance = balance;
    }

    public BigDecimal getBalance() { 
        return balance; 
    }

    public String getPixKey() {
        return pixKey;
    }
}
