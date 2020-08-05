package com.ironhack.accountservice.controller.interfaces;


import com.ironhack.accountservice.model.classes.Account;

import java.util.List;

public interface AccountControllerInterface {
    public List<Account> findAll();
    public List<Account> findByUserId(String userId);
    public Account findById(String accountId);
    public Account createAccount(Account account);
    public Account updateAccount(String id, Account account);
    public void deleteAccount(String id);
}
