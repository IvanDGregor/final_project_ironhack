package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.OperationClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.exception.CreditCardClientNotWorkingException;
import com.ironhack.edgeservice.exception.OperationsClientNotWorkingException;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Transaction;
import com.ironhack.edgeservice.model.dto.PaymentDTO;
import com.ironhack.edgeservice.model.dto.TransferDTO;
import com.ironhack.edgeservice.model.enums.TypeTransaction;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;
    @MockBean
    private OperationClient operationClient;

    private List<Transaction> transactionList1;
    private List<Transaction> transactionList2;
    private Transaction transaction1;
    private Transaction transaction2;
    private PaymentDTO paymentDTO1;
    private TransferDTO transferDTO1;

    @BeforeEach
    void setUp() {
        transferDTO1 = new TransferDTO("ES4500","ES4501",new BigDecimal("100"),"1234");
        paymentDTO1 = new PaymentDTO("1234567","1234",new BigDecimal("120"));
        transaction1 = new Transaction("ES4500","ES4501","12345678A",new BigDecimal("400"), LocalDateTime.of(2020,1,1,10,00), TypeTransaction.TRANSFER);
        transaction2 = new Transaction("1234567","ES4501","12345678A",new BigDecimal("400"), LocalDateTime.of(2020,1,1,10,00), TypeTransaction.CREDITCARD);
        transactionList1 = Arrays.asList(transaction1, transaction2);
        transactionList2 = Arrays.asList(transaction1);
    }

    @Test
    void transfer() {
        doNothing().when(operationClient).transfer(Mockito.anyString(), Mockito.any(TransferDTO.class));
        transactionService.transfer(new TransferDTO());
    }

    @Test
    void transferNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(operationClient).transfer(Mockito.anyString(), Mockito.any(TransferDTO.class));
        assertThrows(OperationsClientNotWorkingException.class, () -> {transactionService.transfer(new TransferDTO());});
    }

    @Test
    void payment() {
        doNothing().when(operationClient).payment(Mockito.anyString(), Mockito.any(PaymentDTO.class));
        transactionService.payment(new PaymentDTO());
    }

    @Test
    void paymentNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(operationClient).payment(Mockito.anyString(), Mockito.any(PaymentDTO.class));
        assertThrows(OperationsClientNotWorkingException.class, () -> {transactionService.payment(new PaymentDTO());});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findAllByUserId() throws Exception {
        when(operationClient.findAllByUserId(Mockito.anyString(), Mockito.anyString())).thenReturn(transactionList1);
        List<Transaction> transactions = transactionService.findAllByUserId("12345678A");
        assertEquals(2, transactions.size());
    }

    @Test
    void findAllByUserIdNotAvailable() {
        when(operationClient.findAllByUserId(Mockito.anyString(), Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(OperationsClientNotWorkingException.class, () -> {transactionService.findAllByUserId("12345678A");});
    }
}