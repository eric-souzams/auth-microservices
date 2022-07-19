package com.bank.loans.services;

import com.bank.loans.models.Loan;

public interface LoanService {

    Loan findByCustomerId(Long customerId);

}
