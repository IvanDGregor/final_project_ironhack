package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.UserController;
import com.ironhack.userservice.exceptions.NoSuchUserException;
import com.ironhack.userservice.exceptions.UserAlreadyExistsException;
import com.ironhack.userservice.model.classes.Role;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.model.dto.CreateUserDTO;
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
     * Finds a User with a username param
     * @return Returns a user with a username param.
     */
    @GetMapping("/user/username/{username}")
    @Override
    public User getByUsername(@PathVariable(name = "username") String username) {
        return userService.getByUsername(username);
    }

    /**
     * Find a User with user id
     * @param user_id Receives the User Id.
     * @return Returns the User with user id.
     */
    @GetMapping("/user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "user_id") String user_id) {
        return userService.findById(user_id);
    }
    /**
     * Creates a new User.
     * @param userDTO Receives the User Object by Body.
     * @return Returns the new User.
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserDTO userDTO) throws UserAlreadyExistsException {
        userService.createUser(userDTO);
    }

    /**
     * Updates a User.
     * @param id Receives the Id of the User to update by Param.
     * @param user Receives a User Object with the information to update by Body.
     */
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    /**
     * Deletes a User by Id.
     * @param id Receives the Id of the User to delete by Param.
     */
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
    }

}
