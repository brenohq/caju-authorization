package com.brenohq.caju_authorization.service.impl;

import com.brenohq.caju_authorization.model.Transaction;
import com.brenohq.caju_authorization.model.dto.CreateTransactionDto;
import com.brenohq.caju_authorization.repository.TransactionRepository;
import com.brenohq.caju_authorization.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public void createTransaction(CreateTransactionDto dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new IllegalArgumentException("Transaction is empty");
        }

        Transaction transaction = Transaction.builder()
                .account(dto.getAccount())
                .amount(dto.getAmount())
                .mcc(dto.getMcc())
                .merchant(dto.getMerchant())
                .build();

        transactionRepository.save(transaction);
    }
}
