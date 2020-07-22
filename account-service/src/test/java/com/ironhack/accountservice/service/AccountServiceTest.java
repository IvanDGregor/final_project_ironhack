package com.ironhack.accountservice.service;

import com.ironhack.accountservice.model.classes.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    public Account account1;
    public Account account2;
    public List<Account> accountList;

    @BeforeEach
    void setUp() {
        account1 = new Account("ES400","1234",new BigDecimal("400"),"1234567A");
        account2 = new Account("ES401","1234",new BigDecimal("400"),"1234567A");
        accountList = new ArrayList<Account>();
        accountList.add(account1);
        accountList.add(account2);
        accountService.createAccount(account1);
        accountService.createAccount(account2);
    }



    @Test
    void findById() {
        Account targetAccount = accountService.findById(account1.getId());
        assertNotNull(targetAccount);
    }

    @Test
    @Order(0)
    void findAll() {
        assertEquals(3, accountService.findAll().size());
    }

    @Test
    void createAccount() {
        Account testAccount = new Account("ES500","1234",new BigDecimal("500"),"1234567A");
        accountList.add(testAccount);
        accountService.createAccount(testAccount);
        assertEquals(4, accountService.findAll().size());
    }

    @Test
    void updateAccount() {
        Account updatedAccount = new Account("ES500","1234",new BigDecimal("500"),"1234567A");
        accountService.updateAccount(account1.getId(), updatedAccount);
        Optional<Account> targetAccount = Optional.ofNullable(accountService.findById(account1.getId()));
        assertEquals(updatedAccount.getUserId(), targetAccount.get().getUserId());
    }

    @Test
    void deleteAccount() {
        accountService.deleteAccount(account1.getId());
        assertEquals(2, accountService.findAll().size());
    }

}