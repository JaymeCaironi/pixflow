package com.jaymecaironi.pixflow.application.port.out;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository {
    boolean debit(String pixKey, BigDecimal amount);
    Optional<BigDecimal> findBalance(String pixKey);
}
