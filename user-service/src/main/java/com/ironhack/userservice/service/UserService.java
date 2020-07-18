package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.NoSuchUserException;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchUserException("There's no user with provided username"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
