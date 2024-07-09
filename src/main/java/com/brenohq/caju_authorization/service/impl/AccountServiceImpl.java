package com.brenohq.caju_authorization.service.impl;

import com.brenohq.caju_authorization.model.Account;
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
