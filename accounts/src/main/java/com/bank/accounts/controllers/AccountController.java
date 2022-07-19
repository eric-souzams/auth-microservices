package com.bank.accounts.controllers;

import com.bank.accounts.models.Account;
import com.bank.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable("id") Long customerId) {
        Account account = accountService.findAccountByCustomerId(customerId);

        return ResponseEntity.ok(account);
    }

}
