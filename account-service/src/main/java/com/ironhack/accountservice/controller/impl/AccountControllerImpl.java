package com.ironhack.accountservice.controller.impl;

import com.ironhack.accountservice.controller.interfaces.AccountControllerInterface;
import com.ironhack.accountservice.exception.AccountNotFoundException;
import com.ironhack.accountservice.model.Account;
import com.ironhack.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountControllerImpl implements AccountControllerInterface {
    @Autowired
    private AccountService accountService;
    /**
     * Finds a List of Accounts
     * @return Returns a list of all Accounts.
     */
    @GetMapping("/accounts")
    @Override
    public List<Account> findAll() {
        return accountService.findAll();
    }
    /**
     * Finds a Account by account id.
     * @param account_id Receives the Account for searching by Param.
     * @return Returns a Account matching the given username.
     * @throws AccountNotFoundException a Exception
     */
    @GetMapping("/account/{account_id}")
    @Override
    public Account findById(@PathVariable(name = "account_id") Integer account_id) {
        return accountService.findById(account_id);
    }

    /**
     * Creates a new Account.
     * @param account Receives the Account Object by Body.
     * @return Returns the new Account.
     */
    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     * Updates a Account.
     * @param id Receives the Id of the Account to update by Param.
     * @param account Receives a Account Object with the information to update by Body.
     */
    @PutMapping("/account/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccountById(@PathVariable Integer id, @RequestBody Account account){
        accountService.updateAccountById(id, account);
    }

    /**
     * Deletes a Account by Id.
     * @param id Receives the Id of the Account to delete by Param.
     */
    @DeleteMapping("/account/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable Integer id) {
        accountService.deleteAccountById(id);
    }
}
