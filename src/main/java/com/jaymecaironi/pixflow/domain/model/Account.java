package com.jaymecaironi.pixflow.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Account(UUID id, String pixKey, BigDecimal balance) {
    public Account {
        if (pixKey == null || pixKey.isBlank()) {
            throw new IllegalArgumentException("Chave Pix é obrigatória");
        }
        if (balance == null || balance.signum() < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo");
        }
    }
}
