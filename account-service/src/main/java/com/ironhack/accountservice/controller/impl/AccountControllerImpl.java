package com.ironhack.accountservice.controller.impl;

import com.ironhack.accountservice.controller.interfaces.AccountControllerInterface;
import com.ironhack.accountservice.exception.AccountNotFoundException;
import com.ironhack.accountservice.model.classes.Account;
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
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll() {
        return accountService.findAll();
    }
    /**
     * Finds a Account by account id.
     * @param accountId Receives the Account for searching by Param.
     * @return Returns a Account matching the given username.
     * @throws AccountNotFoundException a Exception
     */
    @GetMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable(name = "accountId") String accountId) {
        return accountService.findById(accountId);
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
    public Account updateAccount(@PathVariable String id, @RequestBody Account account){
        return accountService.updateAccount(id, account);
    }

    /**
     * Deletes a Account by Id.
     * @param id Receives the Id of the Account to delete by Param.
     */
    @DeleteMapping("/account/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}
