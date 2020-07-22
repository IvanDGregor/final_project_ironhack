package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.model.classes.Account;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountClient accountClient;

    private List<Account> accountList1;
    private List<Account> accountList2;
    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        account1 = new Account("ES4500","1234",new BigDecimal("1000"),"12345678A");
        account2 = new Account("ES4501","1234",new BigDecimal("1000"),"12345678B");
        accountList1 = Arrays.asList(account1, account2);
        accountList2 = Arrays.asList(account1);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findAll() throws Exception {
        when(accountClient.findAll(Mockito.anyString())).thenReturn(accountList1);
        List<Account> accounts = accountService.findAll();
        assertEquals(2, accounts.size());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notGetAllAccounts() throws Exception{
        when(accountClient.findAll(Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(AccountClientNotWorkingException.class, () -> {accountService.findAll();});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findById() {
        when(accountClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(account1);
        assertEquals(account1, accountService.findById("12345678A"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notFindByIdAccount() {
        when(accountClient.findById(Mockito.anyString(), Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(AccountClientNotWorkingException.class, () -> {accountService.findById("12345678A");});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createAccount() {
        when(accountClient.createAccount(Mockito.anyString(), Mockito.any(Account.class))).thenReturn(account1);
        assertEquals(account1, accountService.createAccount(account1));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createNotAvailable() {
        when(accountClient.createAccount(Mockito.anyString(), Mockito.any(Account.class))).thenThrow(FeignException.FeignClientException.class);
        assertThrows(AccountClientNotWorkingException.class, () -> {accountService.createAccount(new Account());});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateAccount() {
        Account updateAccount = new Account("ES4500","1234",new BigDecimal("1000"),"12345678A");
        doNothing().when(accountClient).updateAccount(Mockito.anyString(), Mockito.anyString(), Mockito.any(Account.class));
        when(accountClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(account1);
        accountService.updateAccount("ES4500",updateAccount);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateAccountNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(accountClient).updateAccount(Mockito.anyString(), Mockito.anyString(), Mockito.any(Account.class));
        assertThrows(AccountClientNotWorkingException.class, () -> {accountService.updateAccount("ES4500",new Account());});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteAccount() {
        doNothing().when(accountClient).deleteAccount(Mockito.anyString(), Mockito.anyString());
        when(accountClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(account1);
        accountService.deleteAccount("ES4500");
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteAccountNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(accountClient).deleteAccount(Mockito.anyString(), Mockito.anyString());
        assertThrows(AccountClientNotWorkingException.class, () -> {accountService.deleteAccount("ES4500");});
    }
}