package org.example.repository;

import org.example.entity.Accessory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends MongoRepository<Accessory, String> {
}
