package com.brenohq.caju_authorization.service;

import com.brenohq.caju_authorization.model.Transaction;
import com.brenohq.caju_authorization.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader {

    private final TransactionRepository transactionRepository;

    public DataLoader(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void init() {
        if (transactionRepository.findAll().isEmpty()) {
            transactionRepository.save(new Transaction("01", new BigDecimal("100.00"), "5432", "UBER"));
            transactionRepository.save(new Transaction("02", new BigDecimal("50.00"), "5431", "IFOOD"));
        }
    }
}
