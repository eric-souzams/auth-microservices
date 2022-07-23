package com.bank.loans.services.impl;

import com.bank.loans.models.Loan;
import com.bank.loans.repositories.LoanRepository;
import com.bank.loans.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> findByCustomerId(Long customerId) {
        return loanRepository.findByCustomerIdOrderByStartDateDesc(customerId);
    }
}
