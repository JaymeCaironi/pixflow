package com.jaymecaironi.pixflow.adapter.in.rest;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

import com.jaymecaironi.pixflow.domain.InsufficientBalanceException;

@RestControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, Object> handleInsufficientBalance(InsufficientBalanceException ex) {
        return Map.of(
            "error", "INSUFFICIENT_BALANCE",
            "message", ex.getMessage(),
            "timestamp", Instant.now().toString()
        );
    }
}
