package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.DataNotFoundException;
import com.ironhack.userservice.exceptions.NoSuchUserException;
import com.ironhack.userservice.exceptions.UserAlreadyExistsException;
import com.ironhack.userservice.model.classes.Role;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.model.dto.CreateUserDTO;
import com.ironhack.userservice.repository.RoleRepository;
import com.ironhack.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void getAll() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, userService.getAll().size());
    }

    @Test
    public void getByUsername_noUser_throwException() {
        assertThrows(NoSuchUserException.class, () -> { userService.getByUsername("admin");});
    }

    @Test
    public void getByUsername_User_throwException() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1");
        user.setRoles(new HashSet<>());
        when(userRepository.findByUsername(Mockito.any(String.class))).thenReturn(Optional.of(user));
        User foundUser = userService.getByUsername("admin");
        assertEquals("1", foundUser.getUserId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("testPassword", foundUser.getPassword());
        assertEquals(0, foundUser.getRoles().size());
    }

    @Test
    void findById() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1");
        user.setRoles(new HashSet<>());
        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
        User foundUser = userService.findById("1");
        assertEquals("1", foundUser.getUserId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("testPassword", foundUser.getPassword());
        assertEquals(0, foundUser.getRoles().size());
    }

    @Test
    void createUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1");
        user.setRoles(new HashSet<>());
        Role newRole = new Role("ADMIN_ROLE", user);
        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        when(roleRepository.save(newRole)).thenReturn(newRole);
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setUserId("1234A");
        userDTO.setUsername("test");
        userDTO.setPassword("testPassword");
        userDTO.setRole("ADMIN_ROLE");
        userDTO.setSurname("Lopez");
        userDTO.setDate_birth(LocalDateTime.of(2020,1,1,00,00,00));
        userService.createUser(userDTO);
        assertEquals("1", user.getUserId());
        assertEquals("test", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertEquals(0, user.getRoles().size());
    }

    @Test
    public void createUser_userAlreadyExists() {
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setUserId("1234A");
        userDTO.setUsername("test");
        userDTO.setPassword("testPassword");
        userDTO.setRole("ADMIN_ROLE");
        userDTO.setSurname("Lopez");
        userDTO.setDate_birth(LocalDateTime.of(2020,1,1,00,00,00));
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1234A");
        user.setRoles(new HashSet<>());
        Role newRole = new Role("ADMIN_ROLE", user);
        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyExistsException.class, () -> { userService.createUser(userDTO);});
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1");
        user.setRoles(new HashSet<>());
        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
        User foundUser = userService.findById("1");
        foundUser.setUserId("1");
        foundUser.setPassword("1234");
        foundUser.setUsername("test");
        foundUser.setDate_birth(LocalDateTime.of(2020,1,1,00,00,00));
        when(userRepository.save(foundUser)).thenReturn(foundUser);
        userService.updateUser("1", foundUser);
        assertEquals("1", foundUser.getUserId());
        assertEquals("test", foundUser.getUsername());
        assertEquals("1234", foundUser.getPassword());
        assertEquals(0, foundUser.getRoles().size());
    }

    @Test
    void updateUser_throwException() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1");
        user.setRoles(new HashSet<>());
        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> { userService.updateUser("1", user);});
    }

    @Test
    void deleteUserById() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("testPassword");
        user.setUserId("1");
        user.setRoles(new HashSet<>());
        when(userRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        userService.deleteUserById(user.getUserId());
        assertEquals(0, userService.getAll().size());
    }
}
