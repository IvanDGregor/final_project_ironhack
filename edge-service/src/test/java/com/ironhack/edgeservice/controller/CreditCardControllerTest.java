package com.ironhack.edgeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.CreditCardClient;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.security.CustomSecuredUser;
import com.ironhack.edgeservice.service.AccountService;
import com.ironhack.edgeservice.service.CreditCardService;
import com.ironhack.edgeservice.service.UserService;
import com.ironhack.edgeservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditCardControllerTest {

    @Autowired
    private CreditCardController creditCardController;
    @MockBean
    private CreditCardService creditCardService;
    @MockBean
    private CreditCardClient creditCardClient;
    @MockBean
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public WebApplicationContext webApplicationContext;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public MockMvc mockMvc;
    private CustomSecuredUser user;
    private CreditCard creditCard;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        User newUser = new User();
        newUser.setUserId("12345678A");
        newUser.setUsername("user");
        Role role = new Role("ROLE_ADMIN");
        newUser.getRoles().add(role);
        newUser.setPassword("test");
        user = new CustomSecuredUser(newUser);

        CreditCard creditCard = new CreditCard("12345678","1234","12345678A");

        when(userService.loadUserByUsername("user")).thenReturn(user);
        when(creditCardService.findAll()).thenReturn(new ArrayList<>());
        when(creditCardService.findById("12345678")).thenReturn(creditCard);
        when(creditCardService.createCreditCard(new CreditCard())).thenReturn(new CreditCard());
        doNothing().when(creditCardService).updateCreditCard(any(String.class), any(CreditCard.class));
        doNothing().when(creditCardService).deleteCreditCard(any(String.class));
    }

    @Test
    public void tryToAccess_notAuthorized_Unauthorized() throws Exception {
        mockMvc.perform(get("/credit-cards")).andExpect(status().isUnauthorized());
    }

    @Test
    void findAll() throws Exception{
        String result = mockMvc.perform(get("/credit-cards").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.equals("[]"));
    }

    @Test
    void findById() throws Exception{
        String result = mockMvc.perform(get("/credit-card/12345678").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("12345678A"));
    }

    @Test
    void createCreditCard() throws Exception{
        mockMvc.perform(post("/credit-card").with(user(user))
                .content(objectMapper.writeValueAsString(new CreditCard()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCreditCard() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/credit-card/12345678").with(user(user))
                .content(objectMapper.writeValueAsString(new CreditCard()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAccount() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/credit-card/ES4500").with(user(user)))
                .andExpect(status().isNoContent());
    }
}