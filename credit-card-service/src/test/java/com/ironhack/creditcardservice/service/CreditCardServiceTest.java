package com.ironhack.creditcardservice.service;

import com.ironhack.creditcardservice.model.classes.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreditCardServiceTest {

    @Autowired
    CreditCardService creditCardService;

    public CreditCard creditCard1;
    public CreditCard creditCard2;
    public List<CreditCard> creditCardList;

    @BeforeEach
    void setUp() {
        creditCard1 = new CreditCard("1111222233334444","1234","1234567A");
        creditCard2 = new CreditCard("4444222233331111","1234","1234567B");
        creditCardList = new ArrayList<CreditCard>();
        creditCardList.add(creditCard1);
        creditCardList.add(creditCard2);
        creditCardService.createCreditCard(creditCard1);
        creditCardService.createCreditCard(creditCard2);
    }

    @Test
    void findById() {
        CreditCard targetCreditCard = creditCardService.findById(creditCard1.getId());
        assertNotNull(targetCreditCard);
    }

    @Test
    @Order(0)
    void findAll() {
        assertEquals(2, creditCardService.findAll().size());
    }

    @Test
    void createCreditCard() {
        CreditCard testCreditCard = new CreditCard("111122223333","1234","11111111A");
        creditCardList.add(testCreditCard);
        creditCardService.createCreditCard(testCreditCard);
        assertEquals(3, creditCardService.findAll().size());
    }

    @Test
    void updateCreditCardById() {
        CreditCard updatedCreditCard = new CreditCard("1111222233334444","4321","1234567A");
        creditCardService.updateCreditCardById(creditCard1.getId(), updatedCreditCard);
        Optional<CreditCard> targetCreditCard = Optional.ofNullable(creditCardService.findById(creditCard1.getId()));
        assertEquals(updatedCreditCard.getPin(), targetCreditCard.get().getPin());
    }

    @Test
    void deleteCreditCardById() {
        creditCardService.deleteCreditCardById(creditCard1.getId());
        assertEquals(1, creditCardService.findAll().size());
    }


}
