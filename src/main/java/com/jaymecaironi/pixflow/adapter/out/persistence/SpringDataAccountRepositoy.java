package com.jaymecaironi.pixflow.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataAccountRepositoy extends JpaRepository<AccountJpaEntity, UUID> {
    
    @Modifying
    @Query("UPDATE AccountJpaEntity a SET a.balance = a.balance - :amount " +
        "WHERE a.pixKey = :pixKey AND a.balance >= :amount")
        int debitIfSufficient(@Param("pixKey") String pixKey, @Param("amount") BigDecimal amount);
    
    @Query("SELECT a.balance FROM AccountJpaEntity a WHERE a.pixKey = :pixKey")
    Optional<BigDecimal> findBalanceByPixKey(@Param("pixKey") String pixKey);
}
