package com.jaymecaironi.pixflow.config;

import com.jaymecaironi.pixflow.application.CreateTransactionService;
import com.jaymecaironi.pixflow.application.port.in.CreateTransactionUseCase;
import com.jaymecaironi.pixflow.application.port.out.TransactionEventPublisher;
import com.jaymecaironi.pixflow.application.port.out.TransactionRepository;
import com.jaymecaironi.pixflow.domain.service.FraudRule;
import com.jaymecaironi.pixflow.domain.service.HighAmountRule;
import com.jaymecaironi.pixflow.application.port.out.AccountRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    
    @Bean
    CreateTransactionUseCase createTransactionUseCase(TransactionRepository transactionRepository,
                                                    TransactionEventPublisher transactionEventPublisher,
                                                    AccountRepository accountRepository) {
        return new CreateTransactionService(transactionRepository, transactionEventPublisher, accountRepository);
    }

    @Bean
    FraudRule highAmountRule() {
        return new HighAmountRule();
    }
}
