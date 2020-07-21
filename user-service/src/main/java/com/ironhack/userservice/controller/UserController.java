package com.ironhack.userservice.controller;

import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.model.dto.CreateUserDTO;

import java.util.List;

public interface UserController {
    public void createUser(CreateUserDTO userDTO);
    public void updateUser(String id, User user);
    public void deleteUserById(String id);
    public List<User> getUsers();
    public User getByUsername(String username);
    public User findById(String id);
}
