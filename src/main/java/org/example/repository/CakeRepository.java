package org.example.repository;

import org.example.entity.Cake;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends MongoRepository<Cake, String> {
    List<Cake> findCakeByCakeType(String type);
}
