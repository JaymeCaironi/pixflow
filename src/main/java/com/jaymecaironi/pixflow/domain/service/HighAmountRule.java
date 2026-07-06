package com.jaymecaironi.pixflow.domain.service;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.math.BigDecimal;
import java.util.Optional;

public class HighAmountRule implements FraudRule {
    private static final BigDecimal LIMIT = new BigDecimal("10000.00");

    @Override
    public Optional<String> evaluate(Transaction tx) {
        return tx.amount().compareTo(LIMIT) > 0 
            ? Optional.of("Valor acima de 10.000,00 (limite Pix noturno)")
            : Optional.empty();
    }
}
