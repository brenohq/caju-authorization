package com.brenohq.caju_authorization.service.impl;

import com.brenohq.caju_authorization.mapper.BalanceMapper;
import com.brenohq.caju_authorization.model.Account;
import com.brenohq.caju_authorization.model.AuthorizationResponse;
import com.brenohq.caju_authorization.model.Transaction;
import com.brenohq.caju_authorization.model.dto.CreateAccountDto;
import com.brenohq.caju_authorization.repository.AccountRepository;
import com.brenohq.caju_authorization.repository.TransactionRepository;
import com.brenohq.caju_authorization.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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
        // - Implementar as regras de negócio L2 e L3
        // - Usar enums para lidar com códigos de resposta e MCCs conhecidos
        // - Usar @Transactional

        BigDecimal transactionAmount = transaction.getAmount();

        if (transactionAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return new AuthorizationResponse("07");
        }

        String mcc = transaction.getMcc();

        Function<Account, BigDecimal> getter = BalanceMapper.getGetter(mcc);
        BiConsumer<Account, BigDecimal> setter = BalanceMapper.getSetter(mcc);

        BigDecimal balance = getter.apply(account);

        if (balance.compareTo(transactionAmount) >= 0) {
            setter.accept(account, balance.subtract(transactionAmount));

            accountRepository.save(account);
            transactionRepository.save(transaction);

            return new AuthorizationResponse("01");
        } else {
            return new AuthorizationResponse("51");
        }
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
