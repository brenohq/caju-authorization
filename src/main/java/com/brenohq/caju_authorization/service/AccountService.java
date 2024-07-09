package com.brenohq.caju_authorization.service;

import com.brenohq.caju_authorization.model.Account;
import com.brenohq.caju_authorization.model.dto.CreateAccountDto;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    void createAccount (CreateAccountDto dto);
}

