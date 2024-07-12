package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoAuditing

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}