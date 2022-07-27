package com.bank.accounts.controllers;

import com.bank.accounts.models.*;
import com.bank.accounts.services.AccountService;
import com.bank.accounts.services.clients.CardsFeignClient;
import com.bank.accounts.services.clients.LoansFeignClient;
import com.bank.accounts.services.impl.AccountServiceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Bulkhead(name = "bulkheadForCustomerDetails", fallbackMethod = "fallbackDetailsForCustomerSupportApp")
    public ResponseEntity<Account> getAccount(@RequestHeader("bank-correlation-id") String correlationId, @PathVariable("id") Long customerId) {
        Account account = accountService.findAccountByCustomerId(customerId);

        return ResponseEntity.ok(account);
    }

    @GetMapping("/my-account/{id}")
    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "fallbackDetailsForCustomerSupportApp")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "fallbackDetailsForCustomerSupportApp")
    public ResponseEntity<AccountDetailsDto> myAccountDetails(@RequestHeader("bank-correlation-id") String correlationId, @PathVariable("id") Long customerId) {
        Account account = accountService.findAccountByCustomerId(customerId);
        List<Loan> loans = loansFeignClient.getLoanDetails(correlationId, customerId);
        List<Card> cards = cardsFeignClient.getCardDetails(correlationId, customerId);

        AccountDetailsDto response = AccountDetailsDto.builder()
                .account(account)
                .cards(cards)
                .loans(loans)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/properties")
    @RateLimiter(name = "rateLimiterForCustomerDetails", fallbackMethod = "fallbackDetailsForCustomerSupportApp2")
    public ResponseEntity<String> getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountServiceConfig.getMsg(), accountServiceConfig.getBuildVersion(),
                accountServiceConfig.getMailDetails(), accountServiceConfig.getActiveBranches());

        return ResponseEntity.ok(ow.writeValueAsString(properties));
    }

    private ResponseEntity<?> fallbackDetailsForCustomerSupportApp(Exception exception) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
        response.put("message", "There was a problem trying to connect to the client");

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    private ResponseEntity<?> fallbackDetailsForCustomerSupportApp2(Throwable throwable) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
        response.put("message", "There was a problem trying to connect to the client");

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
