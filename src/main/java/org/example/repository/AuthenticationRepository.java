package org.example.repository;

import org.example.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthenticationRepository extends MongoRepository<Account, String> {
    Optional<Account> findAccountByUsernameAndPassword(String username, String password);
}
