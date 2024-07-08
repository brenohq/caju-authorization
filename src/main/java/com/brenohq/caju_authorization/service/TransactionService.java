package com.brenohq.caju_authorization.service;

import com.brenohq.caju_authorization.model.Transaction;
import com.brenohq.caju_authorization.model.dto.CreateTransactionDto;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();

    void createTransaction (CreateTransactionDto dto);
}
