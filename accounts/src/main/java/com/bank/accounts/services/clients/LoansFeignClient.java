package com.bank.accounts.services.clients;

import com.bank.accounts.models.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/loans/{id}")
    List<Loan> getLoanDetails(@PathVariable("id") Long customerId);

}
