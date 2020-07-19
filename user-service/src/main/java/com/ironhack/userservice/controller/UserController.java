package com.ironhack.userservice.controller;

import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.service.UserService;

import java.util.List;

public interface UserController {
    public List<User> getUsers();
    public User getByUsername(String username);
    public User createUser(User user);
    public void updateUser(Integer id, User user);
    public void deleteUserById(Integer id);
}
