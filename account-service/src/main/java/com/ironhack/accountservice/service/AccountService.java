package com.ironhack.accountservice.service;

import com.ironhack.accountservice.exception.AccountNotFoundException;
import com.ironhack.accountservice.exception.DataNotFoundException;
import com.ironhack.accountservice.model.classes.Account;
import com.ironhack.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    /**
     * This method get a Account whose id attribute
     * @param accountId a Integer value
     * @return A Account which was found
     * @throws AccountNotFoundException if there isn't any Account whose account id doesn't matches accountId param
     */
    public Account findById(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("There's no account with provided id"));
    }
    /**
     * This method return all elements from accountRepository's list
     * @return a Account's list
     */
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * This method creates a new Account and adds in accountRepository's list
     * @param account a Account element
     * @return The account which was added in accountRepository's list
     */
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    /**
     * This method update Account's properties.
     * @param id a integer value to find a exist Account
     * @param account a account element to update a exist account
     * @throws DataNotFoundException if there isn't a user whose id attribute doesn't match with id param
     */
    public Account updateAccount(String id, Account account) throws DataNotFoundException {
        Account accountFound = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find that User."));
        accountFound.setBalance(account.getBalance());
        accountFound.setSecretKey(account.getSecretKey());
        accountFound.setStatus(account.getStatus());
        accountFound.setUserId(account.getUserId());
        return accountRepository.save(accountFound);
    }
    /**
     * This method found a match between a Account's id and param id then these user will be deleted.
     * @param id a integer value to find a exist Account
     * @throws DataNotFoundException if there isn't a Account whose id attribute doesn't match with id param
     */
    public void deleteAccount(String id) throws DataNotFoundException {
        Account accountFound = accountRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find that account."));
        accountFound.setBalance(null);
        accountFound.setSecretKey(null);
        accountFound.setUserId(null);
        accountFound.setStatus(null);
        accountRepository.delete(accountFound);
    }
}
