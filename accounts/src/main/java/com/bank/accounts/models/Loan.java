package com.bank.accounts.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class Loan {

    private Long id;

    private Long customerId;

    private Date startDate;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

    private String createDt;
}
