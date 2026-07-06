package com.jaymecaironi.pixflow.config;

import com.jaymecaironi.pixflow.application.CreateTransactionService;
import com.jaymecaironi.pixflow.application.port.in.CreateTransactionUseCase;
import com.jaymecaironi.pixflow.application.port.out.TransactionEventPublisher;
import com.jaymecaironi.pixflow.application.port.out.TransactionRepository;
import com.jaymecaironi.pixflow.domain.service.FraudRule;
import com.jaymecaironi.pixflow.domain.service.HighAmountRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    
    @Bean
    CreateTransactionUseCase createTransactionUseCase(TransactionRepository transactionRepository,
                                                    TransactionEventPublisher transactionEventPublisher) {
        return new CreateTransactionService(transactionRepository, transactionEventPublisher);
    }

    @Bean
    FraudRule highAmountRule() {
        return new HighAmountRule();
    }
}
