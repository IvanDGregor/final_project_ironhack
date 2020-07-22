package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exception.UserClientNotWorkingException;
import com.ironhack.edgeservice.exception.UserNotFoundException;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.model.dto.CreateUserDTO;
import com.ironhack.edgeservice.security.CustomSecuredUser;
import com.ironhack.edgeservice.util.JwtUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserClient userClient;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    @HystrixCommand(fallbackMethod = "notLoadByUsername")
    public UserDetails loadUserByUsername(String username) {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        System.out.println("Search user with username: " + username);
        try {
            User user = userClient.findByUsername(userToken, username);
            System.out.println(user);
            return new CustomSecuredUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UsernameNotFoundException("Invalid username/password combination.");
        }
    }

    public UserDetails notLoadByUsername(String username) {
        throw new UserClientNotWorkingException("user-service not available!");
    }

    /**
     * This method find all Users in userRepository's list
     * @return The all Users which was added in userRepository's list
     */
    @Secured("ROLE_ADMIN")
    @HystrixCommand(fallbackMethod = "notGetAllUsers")
    public List<User> getAllUsers() {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        return userClient.getAll(userToken);
    }

    public List<User> notGetAllUsers() {
        throw new UserClientNotWorkingException("user-service not available!");
    }


    /**
     * This method find a User and adds in userRepository's list
     * @param id a User id
     * @return The User which was added in userRepository's list
     */
    @HystrixCommand(fallbackMethod = "notFindByIdUser")
    public User findById(String id) throws UserNotFoundException {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        return userClient.findById(userToken, id);
    }

    public User notFindByIdUser(String id) {
        throw new UserClientNotWorkingException("user-service not available!");
    }

    /**
     * This method find a User and adds in userRepository's list
     * @param username a User id
     * @return The User which was added in userRepository's list
     */
    @HystrixCommand(fallbackMethod = "notFindByUsernameUser")
    public User findByUsername(String username) throws UserNotFoundException {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        return userClient.findByUsername(userToken, username);
    }

    public User notFindByUsernameUser(String username) {
        throw new UserClientNotWorkingException("user-service not available!");
    }

    /**
     * This method creates a new User and adds in userRepository's list
     * @param userDTO a User object
     * @return The User which was added in userRepository's list
     */
    @HystrixCommand(fallbackMethod = "createUserNotAvailable")
    public void createUser(CreateUserDTO userDTO) {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        userClient.createUser(userToken, userDTO);
    }
    public void createUserNotAvailable(CreateUserDTO userDTO) {
        throw new UserClientNotWorkingException("user-service not available!");
    }

    /**
     * This method update a User
     * @param user a user element
     * @return The User which was added in userRepository's list
     */
    @HystrixCommand(fallbackMethod = "updateUserNotAvailable")
    public void updateUser(String id, User user) throws UserNotFoundException {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        if (userClient.findById(userToken, id) != null) {
            userClient.updateUser(userToken, id, user);
        } else throw new UserNotFoundException("There's no User with id: " + user.getUserId());
    }
    public void updateUserNotAvailable(String id, User user) {
        throw new UserClientNotWorkingException("user-service not available!");
    }

    /**
     * This method delete User
     * @param id a integer to delete User
     */
    @HystrixCommand(fallbackMethod = "deleteUserNotAvailable")
    public void deleteUser(String id) throws UserNotFoundException {
        String userToken = "Bearer " + jwtUtil.generateToken("user-service");
        if (userClient.findById(userToken, id) != null) {
            userClient.deleteUser(userToken, id);
        } else throw new UserNotFoundException("There's no User with id: " + id);
    }

    public void deleteUserNotAvailable(String id) {
        throw new UserClientNotWorkingException("user-service not available!");
    }
}
