package com.jaymecaironi.pixflow.adapter.in.kafka;

import com.jaymecaironi.pixflow.adapter.out.mongo.FraudAlertDocument;
import com.jaymecaironi.pixflow.adapter.out.mongo.FraudAlertMongoRepository;
import com.jaymecaironi.pixflow.domain.model.Transaction;
import com.jaymecaironi.pixflow.domain.service.FraudRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class FraudDetectionConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(FraudDetectionConsumer.class);

    private final List<FraudRule> rules;
    private final FraudAlertMongoRepository alertRepository;

    public FraudDetectionConsumer(List<FraudRule> rules, FraudAlertMongoRepository alertRepository) {
        this.rules = rules;
        this.alertRepository = alertRepository;
    }

    @KafkaListener(topics = "transactions.created", groupId = "fraud-detection")
    public void onTransactionCreated(Transaction tx) {
        log.info("Analisando transação {}", tx.id());
        rules.forEach(rule -> rule.evaluate(tx).ifPresent(reason -> {
            var alert = new FraudAlertDocument(UUID.randomUUID().toString(), tx.id(), 
                tx.amount(), reason, Instant.now());
            alertRepository.save(alert);
            log.warn("FRAUDE SUSPEITA {} -> {}", tx.id(), reason);
        }));
    }
}
