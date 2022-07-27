package com.bank.loans.controllers;

import com.bank.loans.models.Loan;
import com.bank.loans.models.Properties;
import com.bank.loans.services.LoanService;
import com.bank.loans.services.impl.LoansServiceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoansServiceConfig loansServiceConfig;

    @GetMapping("/{id}")
    public ResponseEntity<List<Loan>> getLoanDetails(@RequestHeader("bank-correlation-id") String correlationId,
                                                     @PathVariable("id") Long customerId) {
        logger.info("method getLoanDetails started");
        List<Loan> customer = loanService.findByCustomerId(customerId);

        logger.info("method getLoanDetails ended");
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
