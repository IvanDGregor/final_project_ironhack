package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.classes.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="account-service")
public interface AccountClient {
    @GetMapping("/accounts")
    public List<Account> findAll(@RequestHeader(name = "Authorization") String token);
    @GetMapping("/account/{account_id}")
    public Account findById(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "account_id") String account_id);
    @GetMapping("/account/user/{userId}")
    public List<Account> findByUserId(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "userId") String userId);
    @PostMapping("/account")
    public Account createAccount(@RequestHeader(name = "Authorization") String token, @RequestBody Account account);
    @PutMapping("/account/{account_id}")
    public void updateAccount(@RequestHeader(name = "Authorization") String token, @PathVariable String account_id, @RequestBody Account account);
    @DeleteMapping("/account/{account_id}")
    public void deleteAccount(@RequestHeader(name = "Authorization") String token, @PathVariable String account_id);

}
