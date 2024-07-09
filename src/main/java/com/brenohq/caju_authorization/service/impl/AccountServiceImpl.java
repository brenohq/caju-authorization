package com.brenohq.caju_authorization.service.impl;

import com.brenohq.caju_authorization.model.Account;
import com.brenohq.caju_authorization.model.AuthorizationResponse;
import com.brenohq.caju_authorization.model.Transaction;
import com.brenohq.caju_authorization.model.dto.CreateAccountDto;
import com.brenohq.caju_authorization.repository.AccountRepository;
import com.brenohq.caju_authorization.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account getAccountById(String accountId) {
        return this.accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));
    }

    @Override
    public AuthorizationResponse authorize(Account account, Transaction transaction) {
        // TODO:
        // - Implementar as regras de negócio L1, L2 e L3
        // - Persistir as transactions no banco e os novos saldos
        return new AuthorizationResponse("01");
    }

    @Override
    public void createAccount(CreateAccountDto dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new IllegalArgumentException("Account is empty");
        }

        Account account = Account.builder()
                .accountId(dto.getAccountId())
                .cashBalance(dto.getCashBalance())
                .foodBalance(dto.getFoodBalance())
                .mealBalance(dto.getMealBalance())
                .build();

        accountRepository.save(account);
    }
}
