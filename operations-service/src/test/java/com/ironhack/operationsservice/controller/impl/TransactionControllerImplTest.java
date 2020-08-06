package com.ironhack.operationsservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.operationsservice.model.classes.Account;
import com.ironhack.operationsservice.model.classes.CreditCard;
import com.ironhack.operationsservice.model.classes.Transaction;
import com.ironhack.operationsservice.model.dto.PaymentDTO;
import com.ironhack.operationsservice.model.dto.TransferDTO;
import com.ironhack.operationsservice.model.enums.Status;
import com.ironhack.operationsservice.model.enums.TypeTransaction;
import com.ironhack.operationsservice.repository.AccountRepository;
import com.ironhack.operationsservice.repository.CreditCardRepository;
import com.ironhack.operationsservice.repository.TransactionRepository;
import com.ironhack.operationsservice.service.TransactionService;
import com.ironhack.operationsservice.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TransactionControllerImplTest {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;


    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    private JwtUtil jwtUtil;

    public MockMvc mockMvc;

    public final ObjectMapper objectMapper = new ObjectMapper();
    public Transaction transaction1;
    public Transaction transaction2;
    public List<Transaction> transactionList;
    private PaymentDTO paymentDTO1;
    private TransferDTO transferDTO1;
    private String token;
    private Account account1;
    private Account account2;
    private CreditCard creditCard1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        token = "Bearer " + jwtUtil.generateToken("test");
        transaction1 = new Transaction("ES4500","ES4501","1234567A",new BigDecimal("1200"), LocalDateTime.of(2020,1,1,10,00), TypeTransaction.TRANSFER);
        transaction2 = new Transaction("111122223333","ES4501","1234567A",new BigDecimal("1200"), LocalDateTime.of(2020,1,1,10,00), TypeTransaction.CREDITCARD);
        transactionList = new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        account1 = new Account();
        account1.setId("ES4500");
        account1.setUserId("12345678A");
        account1.setStatus(Status.ACTIVE);
        account1.setBalance(new BigDecimal("1000"));
        account1.setSecret_key("1234");
        account2 = new Account();
        account2.setId("ES4501");
        account2.setUserId("12345678A");
        account2.setStatus(Status.ACTIVE);
        account2.setSecret_key("1234");
        account2.setBalance(new BigDecimal("1500"));
        creditCard1 = new CreditCard();
        creditCard1.setId("111122223333");
        creditCard1.setAccountId("ES4500");
        creditCard1.setPin("1234");
        creditCard1.setStatus(Status.ACTIVE);
        creditCard1.setUserId("1234567A");
        creditCardRepository.save(creditCard1);
        accountRepository.save(account1);
        accountRepository.save(account2);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        creditCardRepository.deleteAll();
        transactionRepository.deleteAll();
    }

    @Test
    public void connectionTry_NoTokenSent_Forbidden() throws Exception {
        mockMvc.perform(get("/transactions/1234567A"))
                .andExpect(status().isForbidden());
    }

    @Test
    void findAllByUserId()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/1234567A").header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void transfer()throws Exception {
        transferDTO1 = new TransferDTO("ES4500","ES4501", new BigDecimal("10"),"1234");
        mockMvc.perform(put("/transaction/transfer").header("Authorization", token)
                .content(objectMapper.writeValueAsString(transferDTO1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void payment()throws Exception {
        paymentDTO1 = new PaymentDTO("111122223333","1234",new BigDecimal("30"));
        mockMvc.perform(put("/transaction/payment").header("Authorization", token)
                .content(objectMapper.writeValueAsString(paymentDTO1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void findAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions").header("Authorization", token))
                .andExpect(status().isOk());
    }
}
