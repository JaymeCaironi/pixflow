package com.jaymecaironi.pixflow.domain.service;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.util.Optional;

public interface FraudRule {
    Optional<String> evaluate(Transaction tx);
}
