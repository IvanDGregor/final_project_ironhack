package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.UserController;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @Override
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{username}")
    @Override
    public User getByUsername(@PathVariable(name = "username") String username) {
        return userService.getByUsername(username);
    }
}
