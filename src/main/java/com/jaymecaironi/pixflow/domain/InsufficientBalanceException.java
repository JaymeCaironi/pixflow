package com.jaymecaironi.pixflow.domain;

public class InsufficientBalanceException extends RuntimeException {
    
    public InsufficientBalanceException(String pixKey) {
        super("Saldo insuficiente na conta " + pixKey);
    }
}
