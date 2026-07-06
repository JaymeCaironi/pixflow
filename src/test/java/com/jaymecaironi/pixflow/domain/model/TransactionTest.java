package com.jaymecaironi.pixflow.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    
    @Test
    void deveCriarTransacaoValidaComStatusPending() {
        var tx = Transaction.create("testeOrigem@pix.com", 
            "testeDestino@pix.com", new BigDecimal("100.00"));
        assertNotNull(tx.id());
        assertEquals(TransactionStatus.PENDING, tx.status());
    }

    @Test
    void deveRejeitarValorNegativo() {
        assertThrows(IllegalArgumentException.class, 
            () -> Transaction.create("testeOrigem@pix.com", 
            "testeDestino@pix.com", new BigDecimal("-10.00")));
    }

    @Test
    void deveRejeitarOrigemIgualDestino() {
        assertThrows(IllegalArgumentException.class, 
            () -> Transaction.create("testeOrigem@pix.com", 
            "testeOrigem@pix.com", new BigDecimal("50.00")));
    }

    @Test
    void withStatusDeveGerarNovaInstanciaImutavel() {
        var tx = Transaction.create("testeOrigem@pix.com", 
            "testeDestino@pix.com", new BigDecimal("150.00"));
        var flagged = tx.withStatus(TransactionStatus.FLAGGED);
        assertEquals(TransactionStatus.PENDING, tx.status());
        assertEquals(TransactionStatus.FLAGGED, flagged.status());
    }
}
