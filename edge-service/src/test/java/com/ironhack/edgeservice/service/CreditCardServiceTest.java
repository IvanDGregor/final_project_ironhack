package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.CreditCardClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.exception.CreditCardClientNotWorkingException;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
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
class CreditCardServiceTest {

    @Autowired
    private CreditCardService creditCardService;
    @MockBean
    private CreditCardClient creditCardClient;

    private List<CreditCard> creditCardList1;
    private List<CreditCard> creditCardList2;
    private CreditCard creditCard1;
    private CreditCard creditCard2;


    @BeforeEach
    void setUp() {
        creditCard1 = new CreditCard("12345678","1234", "12345678A");
        creditCard2 = new CreditCard("87654321","4321", "12345678B");
        creditCardList1 = Arrays.asList(creditCard1, creditCard2);
        creditCardList2 = Arrays.asList(creditCard1);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findAll() throws Exception {
        when(creditCardClient.findAll(Mockito.anyString())).thenReturn(creditCardList1);
        List<CreditCard> creditCards = creditCardService.findAll();
        assertEquals(2, creditCards.size());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notGetAllCreditCards() throws Exception{
        when(creditCardClient.findAll(Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(CreditCardClientNotWorkingException.class, () -> {creditCardService.findAll();});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findById() {
        when(creditCardClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(creditCard1);
        assertEquals(creditCard1, creditCardService.findById("12345678"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notFindByIdCreditCard() {
        when(creditCardClient.findById(Mockito.anyString(), Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(CreditCardClientNotWorkingException.class, () -> {creditCardService.findById("12345678A");});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createCreditCard() {
        when(creditCardClient.createCreditCard(Mockito.anyString(), Mockito.any(CreditCard.class))).thenReturn(creditCard1);
        assertEquals(creditCard1, creditCardService.createCreditCard(creditCard1));
    }

    @Test
    void createCreditCardNotAvailable() {
        when(creditCardClient.createCreditCard(Mockito.anyString(), Mockito.any(CreditCard.class))).thenThrow(FeignException.FeignClientException.class);
        assertThrows(CreditCardClientNotWorkingException.class, () -> {creditCardService.createCreditCard(new CreditCard());});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateCreditCard() {
        CreditCard updateCreditCard = new CreditCard("12345678","12345","12345678A");
        doNothing().when(creditCardClient).updateCreditCard(Mockito.anyString(), Mockito.anyString(), Mockito.any(CreditCard.class));
        when(creditCardClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(creditCard1);
        creditCardService.updateCreditCard("12345678",updateCreditCard);
    }

    @Test
    void updateCreditCardNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(creditCardClient).updateCreditCard(Mockito.anyString(), Mockito.anyString(), Mockito.any(CreditCard.class));
        assertThrows(CreditCardClientNotWorkingException.class, () -> {creditCardService.updateCreditCard("12345678",creditCard1);});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteCreditCard() {
        doNothing().when(creditCardClient).deleteCreditCard(Mockito.anyString(), Mockito.anyString());
        when(creditCardClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(creditCard1);
        creditCardService.deleteCreditCard("12345678");
    }

    @Test
    void deleteCreditCardNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(creditCardClient).deleteCreditCard(Mockito.anyString(), Mockito.anyString());
        assertThrows(CreditCardClientNotWorkingException.class, () -> {creditCardService.deleteCreditCard("12345678");});
    }
}