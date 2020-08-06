package com.ironhack.operationsservice.service;

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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public TransferDTO transaction1;
    public PaymentDTO transaction2;
    public TransferDTO transaction3;
    public TransferDTO transaction4;
    public PaymentDTO transaction5;
    public List<Transaction> transactionList;
    public Account account1;
    public Account account2;
    public CreditCard creditCard1;

    @BeforeEach
    void setUp() {
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
        accountRepository.save(account1);
        accountRepository.save(account2);
        creditCard1 = new CreditCard();
        creditCard1.setAccountId("ES4500");
        creditCard1.setId("111122223333");
        creditCard1.setPin("1234");
        creditCard1.setStatus(Status.ACTIVE);
        creditCard1.setUserId("12345678A");
        creditCardRepository.save(creditCard1);
        transaction1 = new TransferDTO("ES4500","ES4501",new BigDecimal("100"),"1234");
        transaction2 = new PaymentDTO("111122223333","1234",new BigDecimal("10"));
        transaction3 = new TransferDTO("ES4500","ES4501",new BigDecimal("100000"),"1234");
        transaction4 = new TransferDTO("ES4500","ES4501",new BigDecimal("100000"),"12");
        transaction5 = new PaymentDTO("111122223333","1234",new BigDecimal("10000000"));
        transactionList = new ArrayList<Transaction>();
        transactionService.transfer(transaction1);
        transactionService.payment(transaction2);
    }
    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        creditCardRepository.deleteAll();
        transactionRepository.deleteAll();
    }

    @Test
    void transfer() {
        transactionService.transfer(transaction1);
        assertEquals(3, transactionService.findAll().size());
    }

    @Test
    void transfer_Not_Enough_Money() {
        assertThrows(Exception.class, () -> transactionService.transfer(transaction3));
    }

    @Test
    void transfer_Not_Valid_Secret_key() {
        assertThrows(Exception.class, () -> transactionService.transfer(transaction4));
    }

    @Test
    void payment() {
        transactionService.payment(transaction2);
        assertEquals(3, transactionService.findAll().size());
    }

    @Test
    void payment_Not_Enough_Money() {
        assertThrows(Exception.class, () -> transactionService.payment(transaction5));
    }

    @Test
    void findAllByUserId() {
        List<Transaction> targetTransaction = transactionService.findAllByUserId("12345678A");
        assertNotNull(targetTransaction);
    }

    @Test
    void findAll() {
        assertEquals(2, transactionService.findAll().size());
    }


}
