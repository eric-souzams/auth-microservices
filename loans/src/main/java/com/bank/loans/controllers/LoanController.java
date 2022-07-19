package com.bank.loans.controllers;

import com.bank.loans.models.Loan;
import com.bank.loans.services.LoanService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanDetails(@PathVariable("id") Long customerId) {
        Loan customer = loanService.findByCustomerId(customerId);

        return ResponseEntity.ok(customer);
    }
}
