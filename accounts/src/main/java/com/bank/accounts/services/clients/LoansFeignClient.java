package com.bank.accounts.services.clients;

import com.bank.accounts.models.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/loans/{id}")
    List<Loan> getLoanDetails(@RequestHeader("bank-correlation-id") String correlationId, @PathVariable("id") Long customerId);

}
