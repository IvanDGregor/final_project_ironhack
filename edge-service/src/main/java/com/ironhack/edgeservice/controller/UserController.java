package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.model.dto.CreateUserDTO;
import com.ironhack.edgeservice.service.AccountService;
import com.ironhack.edgeservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User Controller")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Finds all Users
     * @return Returns all Users
     */
    @GetMapping("/users")
    @ApiOperation(value="Find All Users",
            notes = "Lists all Users created",
            response = User.class)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    /**
     * Finds a User by credit user id.
     * @param user_id Receives the User Id for searching by Param.
     * @return Returns a User matching the given user id.
     */
    @GetMapping("/user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "user_id") String user_id) {
        return userService.findById(user_id);
    }

    /**
     * Finds a User by credit user id.
     * @param username Receives the User Id for searching by Param.
     * @return Returns a User matching the given user id.
     */
    @GetMapping("/user/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable(name = "username") String username) {
        return userService.findByUsername(username);
    }

    /**
     * Create new User
     * @param createUserDTO Receives User object to create User
     * @return Returns a new User created
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
    }

    /**
     * Update User
     * @param user Receives User object to update User
     * @param user_id Receives User id to update User
     * @return Returns a new User created
     */
    @PutMapping("/user/{user_id}")
    @ApiOperation(value="Update User",
            response = User.class)
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable(name = "user_id") String user_id, @RequestBody User user){
        return userService.updateUser(user_id, user);
    }

    /**
     * Delete User
     * @param user_id Receives User id to delete User
     * @return Returns a new User created
     */
    @DeleteMapping("/user/{user_id}")
    @ApiOperation(value="Delete USer",
            response = User.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "user_id") String user_id){
        userService.deleteUser(user_id);
    }

}
