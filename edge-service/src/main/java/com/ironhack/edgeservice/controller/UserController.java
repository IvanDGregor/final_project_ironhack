package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.service.AccountService;
import com.ironhack.edgeservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "User Controller")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation(value="Find All Users",
            notes = "Lists all Users created",
            response = User.class)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll(){
        return userService.getAllUsers();
    }

}
