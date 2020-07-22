package com.ironhack.creditcardservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.creditcardservice.controller.impl.CreditCardControllerImpl;
import com.ironhack.creditcardservice.exception.CreditCardNotFoundException;
import com.ironhack.creditcardservice.exception.DataNotFoundException;
import com.ironhack.creditcardservice.model.classes.CreditCard;
import com.ironhack.creditcardservice.model.enums.Status;
import com.ironhack.creditcardservice.service.CreditCardService;
import com.ironhack.creditcardservice.util.JwtUtil;
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
public class CreditCardControllerImplTest {

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    private CreditCardControllerImpl creditCardController;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    private JwtUtil jwtUtil;

    public MockMvc mockMvc;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public CreditCard creditCard1;
    public CreditCard creditCard2;
    public List<CreditCard> creditCardList;
    private String token;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        token = "Bearer " + jwtUtil.generateToken("test");
        creditCard1 = new CreditCard();
        creditCard1.setId("1111222233334444");
        creditCard1.setPin("1234");
        creditCard1.setStatus(Status.ACTIVE);
        creditCard1.setUser_id("1234567A");
        creditCard2 = new CreditCard();
        creditCard2.setId("4444333322221111");
        creditCard2.setPin("1234");
        creditCard2.setStatus(Status.ACTIVE);
        creditCard2.setUser_id("1234567B");
        creditCardList = new ArrayList<CreditCard>();
        creditCardList.add(creditCard1);
        creditCardList.add(creditCard2);
        creditCardService.createCreditCard(creditCard1);
        creditCardService.createCreditCard(creditCard2);
    }

    @Test
    public void connectionTry_NoTokenSent_Forbidden() throws Exception {
        mockMvc.perform(get("/credit-cards"))
                .andExpect(status().isForbidden());
    }

    @Test
    void findAll()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/credit-cards").header("Authorization", token)).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/credit-card/" + creditCard1.getId()).header("Authorization", token))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1111222233334444"));
    }

    @Test
    void findByIdException() {
        assertThrows(CreditCardNotFoundException.class, () -> {
            creditCardController.findById("AAAAA");
        });
    }

    @Test
    void createCreditCard() throws Exception {
        CreditCard testCreditCard = new CreditCard("1111222233334444","1234","1234567A");
        mockMvc.perform(post("/credit-card").header("Authorization", token)
                .content(objectMapper.writeValueAsString(testCreditCard))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCreditCardById() throws Exception {
        CreditCard testCreditCard = new CreditCard("1111222233334444","1234","1234567A");
        creditCardService.createCreditCard(testCreditCard);
        mockMvc.perform(put("/credit-card/" + testCreditCard.getId()).header("Authorization", token)
                .content(objectMapper.writeValueAsString(testCreditCard))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateCreditCardByIdException() {
        assertThrows(DataNotFoundException.class, () -> {
            creditCardController.updateCreditCardById("AAAA", null);
        });
    }
    @Test
    void deleteCreditCardById() throws Exception {
        CreditCard testCreditCard = new CreditCard("1111222233334444","1234","1234567A");
        creditCardService.createCreditCard(testCreditCard);
        mockMvc.perform(MockMvcRequestBuilders.delete("/credit-card/" + testCreditCard.getId()).header("Authorization", token)).andExpect(status().isNoContent());
    }

    @Test
    void deleteCreditCardByIdException() {
        assertThrows(DataNotFoundException.class, () -> {
            creditCardController.deleteCreditCardById("AAAAA");
        });
    }
}
