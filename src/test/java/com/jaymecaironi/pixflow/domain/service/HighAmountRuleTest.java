package com.jaymecaironi.pixflow.domain.service;

import com.jaymecaironi.pixflow.domain.model.Transaction;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class HighAmountRuleTest {
    
    private final HighAmountRule rule = new HighAmountRule();

   @Test
   void naoDeveAlertarValorDentroDoLimite() {
        var tx = Transaction.create("testeOrigem@pix.com", 
            "testeDestino@pix.com", new BigDecimal("9999.99"));
        assertTrue(rule.evaluate(tx).isEmpty());
   }
   
   @Test
   void deveAlertarValorAcimaDoLimite() {
        var tx = Transaction.create("testeOrigem@pix.com", 
            "testeDestino@pix.com", new BigDecimal("10000.01"));
        assertTrue(rule.evaluate(tx).isPresent());
   }
}
