package com.ironhack.userservice.service;

import com.ironhack.userservice.exceptions.DataNotFoundException;
import com.ironhack.userservice.exceptions.NoSuchUserException;
import com.ironhack.userservice.exceptions.UserAlreadyExistsException;
import com.ironhack.userservice.model.classes.User;
import com.ironhack.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    /**
     * This method get a user whose id attribute matches username param
     * @param username a String value
     * @return  A User which was found
     * @throws NoSuchUserException if there isn't any user whose username doesn't matches username param
     */
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchUserException("There's no user with provided username"));
    }
    /**
     * This method return all elements from userRepository's list
     * @return a User's list
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * This method creates a new User and adds in userRepository's list
     * @param user a user element
     * @return The user which was added in userRepository's list
     */
    public User createUser(User user) throws UserAlreadyExistsException{
        if(userRepository.findById(user.getId()).isPresent()){
            throw new UserAlreadyExistsException("This user already exits");
        }
        else {
            return userRepository.save(user);
        }
    }

    /**
     * This method update User's properties.
     * @param id a integer value to find a exist user
     * @param user a user element to update a exist user
     * @throws DataNotFoundException if there isn't a user whose id attribute doesn't match with id param
     */
    public void updateUser(Integer id, User user) throws DataNotFoundException {
        User userFound = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find that User."));
        userFound.setId(id);
        userFound.setUsername(user.getUsername());
        userFound.setSurname(user.getSurname());
        userFound.setDate_birth(user.getDate_birth());
        userRepository.save(userFound);
    }

    /**
     * This method found a match between a user's id and param id then these user will be deleted.
     * @param id a integer value to find a exist user
     * @throws DataNotFoundException if there isn't a lead whose id attribute doesn't match with id param
     */
    public void deleteUserById(Integer id) throws DataNotFoundException {
        User userFound = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find that lead."));
        userFound.setUsername(null);
        userFound.setSurname(null);
        userFound.setDate_birth(null);
        userRepository.delete(userFound);
    }
}
