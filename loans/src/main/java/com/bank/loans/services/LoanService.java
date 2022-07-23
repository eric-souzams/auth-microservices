package com.bank.loans.services;

import com.bank.loans.models.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> findByCustomerId(Long customerId);

}
