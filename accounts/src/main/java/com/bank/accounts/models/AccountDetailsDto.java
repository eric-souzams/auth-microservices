package com.bank.accounts.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString @Builder
public class AccountDetailsDto {

    private Account account;
    private List<Card> cards;
    private List<Loan> loans;

}
