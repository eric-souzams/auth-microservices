package com.bank.accounts.services.impl;

import com.bank.accounts.models.Account;
import com.bank.accounts.repositories.AccountRepository;
import com.bank.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Account not found."));
    }
}
