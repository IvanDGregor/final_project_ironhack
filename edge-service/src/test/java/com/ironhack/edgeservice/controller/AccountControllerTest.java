package com.ironhack.edgeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.security.CustomSecuredUser;
import com.ironhack.edgeservice.service.AccountService;
import com.ironhack.edgeservice.service.UserService;
import com.ironhack.edgeservice.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    private AccountController accountController;
    @MockBean
    private AccountService accountService;
    @MockBean
    private AccountClient accountClient;
    @MockBean
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public WebApplicationContext webApplicationContext;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public MockMvc mockMvc;
    private CustomSecuredUser user;
    private Account account;

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
        account = new Account("ES4500","1234",new BigDecimal("1000"),"12345678A");

        when(userService.loadUserByUsername("user")).thenReturn(user);
        when(accountService.findAll()).thenReturn(new ArrayList<>());
        when(accountService.findByUserId("12345678A")).thenReturn(new ArrayList<>());
        when(accountService.findById("ES4500")).thenReturn(account);
        when(accountService.createAccount(new Account())).thenReturn(new Account());
        doNothing().when(accountService).updateAccount(any(String.class), any(Account.class));
        doNothing().when(accountService).deleteAccount(any(String.class));
    }

    @Test
    public void tryToAccess_notAuthorized_Unauthorized() throws Exception {
        mockMvc.perform(get("/accounts")).andExpect(status().isUnauthorized());
    }

    @Test
    void findAll() throws Exception{
        String result = mockMvc.perform(get("/accounts").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.equals("[]"));
    }

    @Test
    void findById() throws Exception{
        String result = mockMvc.perform(get("/account/ES4500").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("12345678A"));
    }

    @Test
    void findByUserId() throws Exception{
        String result = mockMvc.perform(get("/account/user/12345678A").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("[]"));
    }

    @Test
    void createAccount() throws Exception{
        mockMvc.perform(post("/account").with(user(user))
                .content(objectMapper.writeValueAsString(new Account()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAccount() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/account/ES4500").with(user(user))
                .content(objectMapper.writeValueAsString(new Account()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAccount() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/ES4500").with(user(user)))
                .andExpect(status().isNoContent());
    }
}