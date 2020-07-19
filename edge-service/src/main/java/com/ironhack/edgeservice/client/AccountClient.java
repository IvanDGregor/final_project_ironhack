package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="account-service")
public interface AccountClient {
    @GetMapping("/accounts")
    public List<Account> findAll(@RequestHeader(name = "Authorization") String token);
    @GetMapping("/users/{account_id}")
    public Account findById(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "account_id") Integer account_id);
    // CREATE
    @PostMapping("/account")
    public Account createAccount(@RequestHeader(name = "Authorization") String token, @RequestBody Account account);
}
