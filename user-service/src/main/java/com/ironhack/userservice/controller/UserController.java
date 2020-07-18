package com.ironhack.userservice.controller;

import com.ironhack.userservice.model.User;

import java.util.List;

public interface UserController {
    public List<User> getUsers();
    public User getByUsername(String username);
}
