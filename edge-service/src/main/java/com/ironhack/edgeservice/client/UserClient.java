package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Role;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.model.dto.CreateUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="user-service")
public interface UserClient {
    @GetMapping("/users")
    public List<User> getAll(@RequestHeader(name = "Authorization") String token);
    @GetMapping("/user/username/{username}")
    public User findByUsername(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "username") String username);
    @GetMapping("/user/{user_id}")
    public User findById(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "user_id") String user_id);
    @PostMapping("/user")
    public void createUser(@RequestHeader(name = "Authorization") String token, @RequestBody CreateUserDTO createUserDTO);
    @PutMapping("/user/{user_id}")
    public void updateUser(@RequestHeader(name = "Authorization") String token, @PathVariable String user_id, @RequestBody User user);
    @DeleteMapping("/user/{user_id}")
    public void deleteUser(@RequestHeader(name = "Authorization") String token, @PathVariable String user_id);
}
