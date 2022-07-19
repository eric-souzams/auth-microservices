package com.bank.accounts.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter @Setter @ToString
public class Account {

    @Id
    @Column(name = "account_number")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_date")
    private LocalDate createDate;

}
