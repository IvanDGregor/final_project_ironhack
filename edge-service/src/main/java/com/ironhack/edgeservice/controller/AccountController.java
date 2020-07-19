package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/accounts")
    @ApiOperation(value="Find All Accounts",
            notes = "Lists all Accounts created",
            response = Account.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @PostMapping("/account")
    @ApiOperation(value="Create New Account",
            response = Account.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Account createLead(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

}
