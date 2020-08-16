package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.CreditCardClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.exception.AccountNotFoundException;
import com.ironhack.edgeservice.exception.CreditCardClientNotWorkingException;
import com.ironhack.edgeservice.exception.CreditCardNotFoundException;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.util.JwtUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CreditCardClient creditCardClient;
    @Autowired
    UserClient userClient;
    @Autowired
    JwtUtil jwtUtil;

    /**
     * This method find all Credit Cards in creditCardRepository's list
     * @return The all Credit Card which was added in creditCardRepository's list
     */
    @HystrixCommand(fallbackMethod = "notGetAllCreditCards")
    public List<CreditCard> findAll() {
        String creditCardToken = "Bearer " + jwtUtil.generateToken("credit-card-service");
        return creditCardClient.findAll(creditCardToken);
    }

    public List<CreditCard> notGetAllCreditCards() {
        throw new CreditCardClientNotWorkingException("credit-card-service not available!");
    }
    /**
     * This method find a Credit Card and adds in creditCardRepository's list
     * @param id a account id
     * @return The Credit Card which was added in creditCardRepository's list
     */
    @HystrixCommand(fallbackMethod = "notFindByIdCreditCard")
    public CreditCard findById(String id) {
        String creditCardToken = "Bearer " + jwtUtil.generateToken("credit-card-service");
        return creditCardClient.findById(creditCardToken, id);
    }

    public CreditCard notFindByIdCreditCard(String id) {
        throw new CreditCardClientNotWorkingException("credit-card-service not available!");
    }

    /**
     * This method find a Credit Card and adds in creditCardRepository's list
     * @param userId a user id
     * @return The Credit Card which was added in creditCardRepository's list
     */
    @HystrixCommand(fallbackMethod = "notFindByUserIdCreditCard")
    public List<CreditCard> findByUserId(String userId) {
        String creditCardToken = "Bearer " + jwtUtil.generateToken("credit-card-service");
        return creditCardClient.findByUserId(creditCardToken, userId);
    }

    public List<CreditCard> notFindByUserIdCreditCard(String userId) {
        throw new CreditCardClientNotWorkingException("credit-card-service not available!");
    }

    /**
     * This method creates a new Credit Card and adds in creditCardRepository's list
     * @param creditCard a account object
     * @return The Account which was added in creditCardRepository's list
     */
    @HystrixCommand(fallbackMethod = "createCreditCardNotAvailable")
    public CreditCard createCreditCard(CreditCard creditCard) {
        String creditCardToken = "Bearer " + jwtUtil.generateToken("credit-card-service");
        return creditCardClient.createCreditCard(creditCardToken, creditCard);
    }
    public CreditCard createCreditCardNotAvailable(CreditCard creditCard) {
        throw new CreditCardClientNotWorkingException("credit-card-service not available!");
    }

    /**
     * This method creates a new Credit Card and adds in creditCardRepository's list
     * @param creditCard a credit card element
     * @return The Credit Card which was added in creditCardRepository's list
     */
    @HystrixCommand(fallbackMethod = "updateCreditCardNotAvailable")
    public void updateCreditCard(String id, CreditCard creditCard) throws CreditCardNotFoundException {
        String creditCardToken = "Bearer " + jwtUtil.generateToken("credit-card-service");
        if (creditCardClient.findById(creditCardToken, id) != null) {
            creditCardClient.updateCreditCard(creditCardToken, id, creditCard);
        } else throw new CreditCardNotFoundException("There's no Credit Card with id: " + creditCard.getId());
    }
    public void updateCreditCardNotAvailable(String id, CreditCard creditCard) {
        throw new CreditCardClientNotWorkingException("credit-card-service not available!");
    }

    /**
     * This method delete Credit Card
     * @param id a integer to delete Credit Card
     */
    @HystrixCommand(fallbackMethod = "deleteCreditCardNotAvailable")
    public void deleteCreditCard(String id) throws CreditCardNotFoundException {
        String creditCardToken = "Bearer " + jwtUtil.generateToken("credit-card-service");
        if (creditCardClient.findById(creditCardToken, id) != null) {
            creditCardClient.deleteCreditCard(creditCardToken, id);
        } else throw new CreditCardNotFoundException("There's no Credit Card with id: " + id);
    }

    public void deleteCreditCardNotAvailable(String id) {
        throw new CreditCardClientNotWorkingException("credit-card-service not available!");
    }

}
