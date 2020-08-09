package com.ironhack.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.controller.impl.UserControllerImpl;
import com.ironhack.userservice.exceptions.NoSuchUserException;
import com.ironhack.userservice.exceptions.UserAlreadyExistsException;
import com.ironhack.userservice.model.classes.Role;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.model.dto.CreateUserDTO;
import com.ironhack.userservice.repository.RoleRepository;
import com.ironhack.userservice.service.UserService;
import com.ironhack.userservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerImplTest {
    @MockBean
    private UserService userService;
    @Autowired
    private UserControllerImpl userController;

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
        user1.getRoles().add(role1);
        user2 = new User();
        user2.setUserId("12345678B");
        user2.setPassword("1234");
        user2.setUsername("Maria");
        user2.setSurname("Lopez");
        user2.setPassword("1234");
        user2.setDate_birth(LocalDateTime.of(2020,1,1,20,00));
        Role role2 = new Role("ROLE_ADMIN",user2);
        user2.getRoles().add(role2);
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

    @Test
    public void getAll_Users_List() throws Exception {
        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("testPassword");
        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("testPassword2");
        when(userService.getAll()).thenReturn(Stream.of(user1, user2).collect(Collectors.toList()));
        mockMvc.perform(get("/users").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("test"))
                .andExpect(jsonPath("$[1].username").value("test2"));
    }

    @Test
    public void getByUsername_ValidUsername_FoundUser() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        when(userService.getByUsername("test")).thenReturn(user);
        mockMvc.perform(get("/user/username/test").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(user.getUsername()))
                .andExpect(jsonPath("password").value(user.getPassword()));

    }

    @Test
    public void getByUsername_InvalidUsername_NotFound() throws Exception {
        when(userService.getByUsername(Mockito.anyString())).thenThrow(NoSuchUserException.class);
        mockMvc.perform(get("/user/username/admin").header("Authorization", token)).andExpect(status().isNotFound());
    }

    @Test
    public void findById_ValidId_FoundUser() throws Exception {
        User user = new User();
        user.setUserId("1");
        user.setUsername("test");
        user.setPassword("testPassword");
        when(userService.findById("1")).thenReturn(user);
        mockMvc.perform(get("/user/1").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userId").value(user.getUserId()))
                .andExpect(jsonPath("username").value(user.getUsername()))
                .andExpect(jsonPath("password").value(user.getPassword()));
    }

    @Test
    public void findById_InvalidUserId_NotFound() throws Exception {
        when(userService.findById(Mockito.anyString())).thenThrow(NoSuchUserException.class);
        mockMvc.perform(get("/user/344").header("Authorization", token)).andExpect(status().isNotFound());
    }

    @Test
    void createUser_Valid_NoUserExists() throws Exception {
        CreateUserDTO user = new CreateUserDTO();
        user.setUserId("1");
        user.setUsername("test");
        user.setPassword("testPassword");
        mockMvc.perform(post("/user").header("Authorization", token)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception {
        String userId = user1.getUserId();
        User userUpdate = new User();
        mockMvc.perform(put("/user/1").header("Authorization", token)
                .param("id", String.valueOf(userId))
                .content(objectMapper.writeValueAsString(userUpdate))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    void deleteById() throws Exception {
        String userId = user1.getUserId();
        mockMvc.perform(delete("/user/1").header("Authorization", token)
                .param("id", String.valueOf(userId))
        ).andExpect(status().isNoContent());
    }
}
