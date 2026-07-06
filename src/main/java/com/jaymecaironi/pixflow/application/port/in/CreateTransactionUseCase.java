package com.jaymecaironi.pixflow.application.port.in;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import java.math.BigDecimal;

public interface CreateTransactionUseCase {
    Transaction create(String originKey, String destinationKey, BigDecimal amount);
}
