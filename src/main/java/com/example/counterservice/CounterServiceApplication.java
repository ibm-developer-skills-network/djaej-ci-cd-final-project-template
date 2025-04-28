package com.example.counterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class CounterServiceApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(CounterServiceApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(CounterServiceApplication.class, args);
        
        logger.info("**************************************************");
        logger.info("  S E R V I C E   R U N N I N G  ");
        logger.info("**************************************************");
    }
}