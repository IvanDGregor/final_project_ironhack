package com.ironhack.edgeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.CreditCardClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.model.dto.CreateUserDTO;
import com.ironhack.edgeservice.security.CustomSecuredUser;
import com.ironhack.edgeservice.service.CreditCardService;
import com.ironhack.edgeservice.service.UserService;
import com.ironhack.edgeservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
class UserControllerTest {

    @Autowired
    private UserController userController;
    @MockBean
    private UserClient userClient;
    @MockBean
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public WebApplicationContext webApplicationContext;

    public final ObjectMapper objectMapper = new ObjectMapper();

    public MockMvc mockMvc;
    private CustomSecuredUser user;
    private User user1;
    private CreateUserDTO createUserDTO1;

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

        createUserDTO1 = new CreateUserDTO();
        createUserDTO1.setUserId("12345678A");
        createUserDTO1.setUsername("Pepe");
        createUserDTO1.setSurname("Macias");
        createUserDTO1.setPassword("1234");
        createUserDTO1.setDate_birth(LocalDateTime.of(2020,1,1,10,00));
        createUserDTO1.setRole("ADMIN_ROLE");

        when(userService.loadUserByUsername("user")).thenReturn(user);
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        when(userService.findById("12345678A")).thenReturn(user);
        when(userService.findByUsername("user")).thenReturn(user);
        doNothing().when(userService).createUser(any(CreateUserDTO.class));
        doNothing().when(userService).updateUser(any(String.class), any(User.class));
        doNothing().when(userService).deleteUser(any(String.class));
    }

    @Test
    public void tryToAccess_notAuthorized_Unauthorized() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isUnauthorized());
    }

    @Test
    void getAll() throws Exception{
        String result = mockMvc.perform(get("/users").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.equals("[]"));
    }

    @Test
    void findById() throws Exception{
        String result = mockMvc.perform(get("/user/12345678A").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("12345678A"));
    }

    @Test
    void findByUsername() throws Exception{
        String result = mockMvc.perform(get("/user/username/user").with(user(user)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("12345678A"));
    }

    @Test
    void createUser() throws Exception{
        mockMvc.perform(post("/user").with(user(user))
                .content(objectMapper.writeValueAsString(new Account()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/user/12345678A").with(user(user))
                .content(objectMapper.writeValueAsString(new Account()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/12345678A").with(user(user)))
                .andExpect(status().isNoContent());
    }
}