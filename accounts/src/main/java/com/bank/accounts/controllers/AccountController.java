package com.bank.accounts.controllers;

import com.bank.accounts.models.*;
import com.bank.accounts.services.AccountService;
import com.bank.accounts.services.clients.CardsFeignClient;
import com.bank.accounts.services.clients.LoansFeignClient;
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

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountServiceConfig accountServiceConfig;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long customerId) {
        Account account = accountService.findAccountByCustomerId(customerId);

        return ResponseEntity.ok(account);
    }

    @GetMapping("/my-account/{id}")
    public ResponseEntity<AccountDetailsDto> myAccountDetails(@PathVariable("id") Long customerId) {
        Account account = accountService.findAccountByCustomerId(customerId);
        List<Loan> loans = loansFeignClient.getLoanDetails(customerId);
        List<Card> cards = cardsFeignClient.getCardDetails(customerId);

        AccountDetailsDto response = AccountDetailsDto.builder()
                .account(account)
                .cards(cards)
                .loans(loans)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountServiceConfig.getMsg(), accountServiceConfig.getBuildVersion(),
                accountServiceConfig.getMailDetails(), accountServiceConfig.getActiveBranches());

        return ow.writeValueAsString(properties);
    }

}
