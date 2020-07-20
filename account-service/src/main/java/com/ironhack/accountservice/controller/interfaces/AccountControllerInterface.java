package com.ironhack.accountservice.controller.interfaces;


import com.ironhack.accountservice.model.Account;

import java.util.List;

public interface AccountControllerInterface {
    public List<Account> findAll();
    public Account findById(Integer account_id);
    public Account createAccount(Account account);
    public Account updateAccount(Integer id, Account account);
    public void deleteAccount(Integer id);
}
