package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.UserController;
import com.ironhack.userservice.exceptions.NoSuchUserException;
import com.ironhack.userservice.exceptions.UserAlreadyExistsException;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    /**
     * Finds a List of Users
     * @return Returns a list of all Users.
     */
    @GetMapping("/users")
    @Override
    public List<User> getUsers() {
        return userService.getAll();
    }

    /**
     * Finds a User by username.
     * @param username Receives the User for searching by Param.
     * @return Returns a User matching the given username.
     * @throws NoSuchUserException a Exception
     */
    @GetMapping("/users/{username}")
    @Override
    public User getByUsername(@PathVariable(name = "username") String username) {
        return userService.getByUsername(username);
    }

    /**
     * Creates a new User.
     * @param user Receives the User Object by Body.
     * @return Returns the new User.
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }

    /**
     * Updates a User.
     * @param id Receives the Id of the User to update by Param.
     * @param user Receives a User Object with the information to update by Body.
     */
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Integer id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    /**
     * Deletes a User by Id.
     * @param id Receives the Id of the User to delete by Param.
     */
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

}
