package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.User;
import com.ironhack.edgeservice.util.JwtUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountClient accountClient;
    @Autowired
    UserClient userClient;

    @Autowired
    JwtUtil jwtUtil;

    @Secured("ROLE_ADMIN")
    @HystrixCommand(fallbackMethod = "notGetAllAccounts")
    public List<Account> findAll() {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        return accountClient.findAll(accountToken);
    }

    public List<Account> notGetAllAccounts() {
        throw new AccountClientNotWorkingException("account-service not available!");
    }

    /**
     * This method creates a new Account and adds in accountRepository's list
     * @param account a lead element
     * @return The Account which was added in accountRepository's list
     */
    @HystrixCommand(fallbackMethod = "createNotAvailable")
    public Account createAccount(Account account) {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        return accountClient.createAccount(accountToken, account);
    }
    public Account createNotAvailable(Account account) {
        throw new AccountClientNotWorkingException("account-service not available!");
    }
}
