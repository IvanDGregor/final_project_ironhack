package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.jcajce.provider.symmetric.ChaCha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Account Controller")
@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Finds a all Accounts.
     * @return Returns all Accounts
     */
    @GetMapping("/accounts")
    @ApiOperation(value="Find All Accounts",
            notes = "Lists all Accounts created",
            response = Account.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
        return accountService.findAll();
    }
    /**
     * Finds a Account by account id.
     * @param account_id Receives the Account for searching by Param.
     * @return Returns a Account matching the given username.
     */
    @GetMapping("/account/{account_id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable(name = "account_id") String account_id) {
        return accountService.findById(account_id);
    }
    /**
     * Finds all Accounts by user id.
     * @param userId Receives the Accounts for searching by Param.
     * @return Returns all Accounts matching the given user id.
     */
    @GetMapping("/account/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findByUserId(@PathVariable(name = "userId") String userId) {
        return accountService.findByUserId(userId);
    }
    /**
     * Create new Account
     * @param account Receives account object to create account
     * @return Returns a new Account created
     */
    @PostMapping("/account")
    @ApiOperation(value="Create New Account",
            response = Account.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     * Update Account
     * @param account Receives account object to create account
     * @return Returns a new Account created
     */
    @PutMapping("/account/{account_id}")
    @ApiOperation(value="Update Account",
            response = Account.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccount(@PathVariable(name = "account_id") String account_id, @RequestBody Account account){
        accountService.updateAccount(account_id, account);
    }

    /**
     * Delete Account
     * @param account_id Receives account object to create account
     * @return Returns a new Account created
     */
    @DeleteMapping("/account/{account_id}")
    @ApiOperation(value="Delete Account",
            response = Account.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(name = "account_id") String account_id){
        accountService.deleteAccount(account_id);
    }
}
