package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exception.CreditCardClientNotWorkingException;
import com.ironhack.edgeservice.exception.UserClientNotWorkingException;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.model.dto.CreateUserDTO;
import com.ironhack.edgeservice.security.CustomSecuredUser;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserClient userClient;

    @Autowired
    private UserService userService;

    private CustomSecuredUser user;
    private CreateUserDTO createUserDTO1;

    @BeforeEach
    void setUp() {
        User newUser = new User();
        newUser.setUserId("12345678A");
        newUser.setUsername("user");
        Role role = new Role("ROLE_ADMIN");
        newUser.getRoles().add(role);
        newUser.setPassword("test");
        user = new CustomSecuredUser(newUser);
        createUserDTO1 = new CreateUserDTO("12345678A","Pepe","Macias","1234",LocalDateTime.of(2020,1,1,10,00),"ADMIN_ROLE");

    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setUserId("12345678A");
        user.setUsername("pepe");
        Role role = new Role("ROLE_ADMIN");
        user.getRoles().add(role);
        user.setPassword("test");
        CustomSecuredUser newUser = new CustomSecuredUser(user);

        when(userClient.findByUsername(Mockito.anyString(),Mockito.anyString())).thenReturn(user);
        assertEquals(newUser, userService.loadUserByUsername("pepe"));
    }
    @Test
    void notLoadByUsername() {
        when(userClient.findByUsername(Mockito.anyString(), Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(UserClientNotWorkingException.class, () -> {userService.loadUserByUsername("pepe");});
    }

    @Test
    void notLoadByUsername_UserNotFound() {
        when(userClient.findByUsername(Mockito.anyString(), Mockito.anyString())).thenThrow(UsernameNotFoundException.class);
        assertThrows(UserClientNotWorkingException.class, () -> {userService.loadUserByUsername("pepe");});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAllUsers() {
        User user = new User();
        user.setUserId("12345678A");
        user.setUsername("pepe");
        Role role = new Role("ROLE_ADMIN");
        user.getRoles().add(role);
        user.setPassword("test");
        List<User> users = Arrays.asList(user);
        when(userClient.getAll(Mockito.anyString())).thenReturn(users);
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notGetAllUsers() {
        when(userClient.getAll(Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(UserClientNotWorkingException.class, () -> {userService.getAllUsers();});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findById() {
        when(userClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        assertEquals(user, userService.findById("12345678A"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notFindByIdUser() {
        when(userClient.findById(Mockito.anyString(), Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(UserClientNotWorkingException.class, () -> {userService.findById("12345678A");});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void findByUsername() {
        when(userClient.findByUsername(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        assertEquals(user, userService.findByUsername("pepe"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void notFindByUsernameUser() {
        when(userClient.findByUsername(Mockito.anyString(), Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);
        assertThrows(UserClientNotWorkingException.class, () -> {userService.findByUsername("pepe");});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createUser() {
        doNothing().when(userClient).createUser(Mockito.anyString(), Mockito.any(CreateUserDTO.class));
        userService.createUser(new CreateUserDTO());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createUserNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(userClient).createUser(Mockito.anyString(),Mockito.any(CreateUserDTO.class));
        assertThrows(UserClientNotWorkingException.class, () -> {userService.createUser(new CreateUserDTO());});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateUser() {
        User updateUser = new User("12345678B","Pepe", "1234","Macias", LocalDateTime.of(2020,1,1,10,00));
        doNothing().when(userClient).updateUser(Mockito.anyString(), Mockito.anyString(), Mockito.any(User.class));
        when(userClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(updateUser);
        userService.updateUser("12345678A",new User());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateUserNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(userClient).updateUser(Mockito.anyString(), Mockito.anyString(), Mockito.any(User.class));
        assertThrows(UserClientNotWorkingException.class, () -> {userService.updateUser("12345678A",new User());});
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteUser() {
        doNothing().when(userClient).deleteUser(Mockito.anyString(), Mockito.anyString());
        when(userClient.findById(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        userService.deleteUser("12345678A");
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteUserNotAvailable() {
        doThrow(FeignException.FeignClientException.class).when(userClient).deleteUser(Mockito.anyString(), Mockito.anyString());
        assertThrows(UserClientNotWorkingException.class, () -> {userService.deleteUser("12345678A");});
    }
}