package com.ironhack.edgeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.OperationClient;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.Transaction;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.model.dto.PaymentDTO;
import com.ironhack.edgeservice.model.dto.TransferDTO;
import com.ironhack.edgeservice.model.enums.TypeTransaction;
import com.ironhack.edgeservice.security.CustomSecuredUser;
import com.ironhack.edgeservice.service.AccountService;
import com.ironhack.edgeservice.service.TransactionService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private OperationClient operationClient;
    @MockBean
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public WebApplicationContext webApplicationContext;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public MockMvc mockMvc;
    private CustomSecuredUser user;
    private Transaction transactionTransfer;
    private Transaction transactionPayment;
    private PaymentDTO paymentDTO1;
    private TransferDTO transferDTO1;


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
        transactionTransfer = new Transaction("ES400","ES4501","12345678A",new BigDecimal("350"), LocalDateTime.of(2020, 1, 1, 10, 00), TypeTransaction.TRANSFER);
        transactionPayment = new Transaction("1234567","ES4500","12345678A",new BigDecimal("350"), LocalDateTime.of(2020, 1, 1, 10, 00), TypeTransaction.CREDITCARD);
        transferDTO1 = new TransferDTO("ES4500","ES4501",new BigDecimal("100"),"1234");
        paymentDTO1 = new PaymentDTO("1234567","1234",new BigDecimal("120"));

        when(userService.loadUserByUsername("user")).thenReturn(user);
        when(transactionService.findAllByUserId("12345678A")).thenReturn(new ArrayList<>());
        doNothing().when(transactionService).transfer(any(TransferDTO.class));
        doNothing().when(transactionService).payment(any(PaymentDTO.class));
    }

    @Test
    public void tryToAccess_notAuthorized_Unauthorized() throws Exception {
        mockMvc.perform(get("/transactions")).andExpect(status().isUnauthorized());
    }

    @Test
    void transfer() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/transaction/transfer").with(user(user))
                .content(objectMapper.writeValueAsString(new Transaction()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void payment() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/transaction/payment").with(user(user))
                .content(objectMapper.writeValueAsString(new Transaction()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void findAllByUserId() throws Exception{
        String result = mockMvc.perform(get("/transactions/12345678A").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.equals("[]"));
    }
}