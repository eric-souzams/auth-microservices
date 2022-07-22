package com.bank.accounts.controllers;

import com.bank.accounts.models.Account;
import com.bank.accounts.models.Properties;
import com.bank.accounts.services.AccountService;
import com.bank.accounts.services.impl.AccountServiceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    @Autowired
    private AccountServiceConfig accountServiceConfig;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable("id") Long customerId) {
        Account account = accountService.findAccountByCustomerId(customerId);

        return ResponseEntity.ok(account);
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountServiceConfig.getMsg(), accountServiceConfig.getBuildVersion(),
                accountServiceConfig.getMailDetails(), accountServiceConfig.getActiveBranches());

        return ow.writeValueAsString(properties);
    }

}
