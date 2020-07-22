package com.ironhack.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.controller.impl.UserControllerImpl;
import com.ironhack.userservice.model.classes.Role;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.repository.RoleRepository;
import com.ironhack.userservice.service.UserService;
import com.ironhack.userservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerImplTest {
    @Autowired
    UserService userService;

    @Autowired
    UserControllerImpl userController;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private JwtUtil jwtUtil;

    public MockMvc mockMvc;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public User user1;
    public User user2;
    public List<User> userList;
    private String token;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        token = "Bearer " + jwtUtil.generateToken("test");
        user1 = new User();
        user1.setUserId("12345678A");
        user1.setPassword("1234");
        user1.setUsername("Pepe");
        user1.setSurname("Macias");
        user1.setPassword("1234");
        user1.setDate_birth(LocalDateTime.of(2020,1,1,20,00));
        Role role1 = new Role("ROLE_ADMIN",user1);
        user2.getRoles().add(role1);
        user2 = new User();
        user2.setUserId("12345678B");
        user2.setPassword("1234");
        user2.setUsername("Maria");
        user2.setSurname("Lopez");
        user2.setPassword("1234");
        user2.setDate_birth(LocalDateTime.of(2020,1,1,20,00));
        Role role2 = new Role("ROLE_ADMIN",user2);
        user1.getRoles().add(role2);
    }

    @Test
    public void connectionTry_NoTokenSent_Forbidden() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAll_NotUsers_EmptyList() throws Exception {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        String response = mockMvc.perform(get("/users").header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("[]", response);
    }

}
