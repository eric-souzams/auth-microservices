package com.bank.accounts.services;

import com.bank.accounts.models.Account;

public interface AccountService {

    Account findAccountByCustomerId(Long customerId);

}
