package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.exception.AccountNotFoundException;
import com.ironhack.edgeservice.model.classes.Account;
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

    /**
     * This method find all Accounts in accountRepository's list
     * @return The all Accounts which was added in accountRepository's list
     */
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
     * This method find a Account and adds in accountRepository's list
     * @param id a account id
     * @return The Account which was added in accountRepository's list
     */
    @HystrixCommand(fallbackMethod = "notFindByIdAccount")
    public Account findById(String id) {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        return accountClient.findById(accountToken, id);
    }

    public Account notFindByIdAccount(String id) {
        throw new AccountClientNotWorkingException("account-service not available!");
    }

    /**
     * This method find all Accounts and adds in accountRepository's list
     * @param userId a account user id
     * @return The Account which was added in accountRepository's list match with user id param
     */
    @HystrixCommand(fallbackMethod = "notFindByUserIdAccount")
    public List<Account> findByUserId(String userId) {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        return accountClient.findByUserId(accountToken, userId);
    }

    public List<Account> notFindByUserIdAccount(String userId) {
        throw new AccountClientNotWorkingException("account-service not available!");
    }

    /**
     * This method creates a new Account and adds in accountRepository's list
     * @param account a account object
     * @return The Account which was added in accountRepository's list
     */
    @Secured("ROLE_ADMIN")
    @HystrixCommand(fallbackMethod = "createNotAvailable")
    public Account createAccount(Account account) {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        return accountClient.createAccount(accountToken, account);
    }
    public Account createNotAvailable(Account account) {
        throw new AccountClientNotWorkingException("account-service not available!");
    }

    /**
     * This method creates a new Account and adds in accountRepository's list
     * @param account a lead element
     * @return The Account which was added in accountRepository's list
     */
    @HystrixCommand(fallbackMethod = "updateAccountNotAvailable")
    public void updateAccount(String id, Account account) throws AccountNotFoundException {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        if (accountClient.findById(accountToken, id) != null) {
            System.out.println("Call client account");
            System.out.println(account.getId() + " " + account.getBalance() + " " + account.getStatus() + " " + account.getSecretKey() + " " + account.getUserId());
            accountClient.updateAccount(accountToken, id, account);
        } else throw new AccountNotFoundException("There's no Account with id: " + account.getId());
    }
    public void updateAccountNotAvailable(String id, Account account) {
        throw new AccountClientNotWorkingException("account-service not available!");
    }

    /**
     * This method delete account
     * @param id a integer to delete account
     */
    @HystrixCommand(fallbackMethod = "deleteAccountNotAvailable")
    public void deleteAccount(String id) throws AccountNotFoundException {
        String accountToken = "Bearer " + jwtUtil.generateToken("account-service");
        if (accountClient.findById(accountToken, id) != null) {
            accountClient.deleteAccount(accountToken, id);
        } else throw new AccountNotFoundException("There's no Account with id: " + id);
    }

    public void deleteAccountNotAvailable(String id) {
        throw new AccountClientNotWorkingException("account-service not available!");
    }
}
