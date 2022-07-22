package com.bank.loans.controllers;

import com.bank.loans.models.Loan;
import com.bank.loans.models.Properties;
import com.bank.loans.services.LoanService;
import com.bank.loans.services.impl.LoansServiceConfig;
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
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoansServiceConfig loansServiceConfig;

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanDetails(@PathVariable("id") Long customerId) {
        Loan customer = loanService.findByCustomerId(customerId);

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());

        return ow.writeValueAsString(properties);
    }
}
