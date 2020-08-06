package com.ironhack.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.accountservice.controller.impl.AccountControllerImpl;
import com.ironhack.accountservice.exception.AccountNotFoundException;
import com.ironhack.accountservice.exception.DataNotFoundException;
import com.ironhack.accountservice.model.classes.Account;
import com.ironhack.accountservice.model.enums.Status;
import com.ironhack.accountservice.service.AccountService;
import com.ironhack.accountservice.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountControllerImplTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountControllerImpl accountController;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    private JwtUtil jwtUtil;

    public MockMvc mockMvc;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public Account account1;
    public Account account2;
    public List<Account> accountList;
    private String token;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        token = "Bearer " + jwtUtil.generateToken("test");
        account1 = new Account();
        account1.setId("ES4500");
        account1.setUserId("12345678A");
        account1.setStatus(Status.ACTIVE);
        account1.setBalance(new BigDecimal("1000"));
        account2 = new Account();
        account2.setId("ES4501");
        account2.setUserId("12345678A");
        account2.setStatus(Status.ACTIVE);
        account2.setBalance(new BigDecimal("1500"));
        accountList = new ArrayList<Account>();
        accountList.add(account1);
        accountList.add(account2);
        accountService.createAccount(account1);
        accountService.createAccount(account2);
    }

    @AfterEach
    void tearDown() {
        accountService.deleteAccount("ES4500");
        accountService.deleteAccount("ES4501");
    }

    @Test
    public void connectionTry_NoTokenSent_Forbidden() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isForbidden());
    }

    @Test
    void findAll()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").header("Authorization", token)).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/account/" + account1.getId()).header("Authorization", token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12345678A"));
    }

    @Test
    void findByUserId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/account/user/" + account1.getUserId()).header("Authorization", token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12345678A"));
    }

    @Test
    void findByIdException() {
        assertThrows(AccountNotFoundException.class, () -> {
            accountController.findById("AAAAA");
        });
    }

    @Test
    void createAccount() throws Exception {
        Account testAccount = new Account("ES405","1234",new BigDecimal("1000"),"12345678A");
        mockMvc.perform(post("/account").header("Authorization", token)
                .content(objectMapper.writeValueAsString(testAccount))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAccount() throws Exception {
        Account testAccount = new Account("ES405","1234",new BigDecimal("1000"),"12345678A");
        accountService.createAccount(testAccount);
        mockMvc.perform(put("/account/" + testAccount.getId()).header("Authorization", token)
                .content(objectMapper.writeValueAsString(testAccount))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateAccountException() {
        assertThrows(DataNotFoundException.class, () -> {
            accountController.updateAccount("AAAA", null);
        });
    }

    @Test
    void deleteAccount() throws Exception {
        Account testAccount = new Account("ES405","1234",new BigDecimal("1000"),"12345678A");
        accountService.createAccount(testAccount);
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/" + testAccount.getId()).header("Authorization", token)).andExpect(status().isNoContent());
    }

    @Test
    void deleteAccountException() {
        assertThrows(DataNotFoundException.class, () -> {
            accountController.deleteAccount("AAAAA");
        });
    }
}
