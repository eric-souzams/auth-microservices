package com.bank.loans.repositories;

import com.bank.loans.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByCustomerIdOrderByStartDateDesc(Long customerId);

}
