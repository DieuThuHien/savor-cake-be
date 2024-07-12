package org.example.repository;

import org.example.entity.Billing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillingRepository extends MongoRepository<Billing, String> {
    List<Billing> findAllByOrderByCreatedDateDesc();
    List<Billing> findAllByStatusOrderByCreatedDateDesc(String status);
    List<Billing> findAllByStatusAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualOrderByCreatedDateDesc(String status, LocalDateTime startDate, LocalDateTime endDate);
}
