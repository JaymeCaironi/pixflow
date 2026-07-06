package com.jaymecaironi.pixflow.adapter.out.kafka;

import com.jaymecaironi.pixflow.application.port.out.TransactionEventPublisher;
import com.jaymecaironi.pixflow.domain.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTransactionEventPublisher implements TransactionEventPublisher {
    
    private static final Logger log = LoggerFactory.getLogger(KafkaTransactionEventPublisher.class);
    public static final String TOPIC = "transactions.created";

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaTransactionEventPublisher(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishCreated(Transaction tx) {
        kafkaTemplate.send(TOPIC, tx.id().toString(), tx)
            .whenComplete((result, ex) -> {
                if (ex != null) log.error("Falha a publicar {}", tx.id(), ex);
                else log.info("Publicado em {}", tx.id(), TOPIC);
            });
    }
}
