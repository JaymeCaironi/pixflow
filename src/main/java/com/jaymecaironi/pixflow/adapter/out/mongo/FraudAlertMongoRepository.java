package com.jaymecaironi.pixflow.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FraudAlertMongoRepository extends MongoRepository<FraudAlertDocument, String> {
}
