package com.ironhack.creditcardservice.service;

import com.ironhack.creditcardservice.exception.CreditCardNotFoundException;
import com.ironhack.creditcardservice.exception.DataNotFoundException;
import com.ironhack.creditcardservice.model.classes.CreditCard;
import com.ironhack.creditcardservice.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    /**
     * This method get a Credit Card whose id attribute
     * @param credit_card_id a String value
     * @return A credit card which was found
     * @throws CreditCardNotFoundException if there isn't any Credit card whose account id doesn't matches credit_card_id param
     */
    public CreditCard findById(String credit_card_id) throws CreditCardNotFoundException{
        return creditCardRepository.findById(credit_card_id).orElseThrow(() -> new CreditCardNotFoundException("There's no credit card with provided id"));
    }
    /**
     * This method return all elements from creditCardRepository's list
     * @return a CreditCard's list
     */
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    /**
     * This method creates a new Credit Card and adds in creditCardRepository's list
     * @param creditCard a Credit Card element
     * @return The Credit card which was added in creditCardRepository's list
     */
    public CreditCard createCreditCard(CreditCard creditCard){
        return creditCardRepository.save(creditCard);
    }

    /**
     * This method update Account's properties.
     * @param id a integer value to find a exist Account
     * @param creditCard a account element to update a exist account
     * @throws DataNotFoundException if there isn't a user whose id attribute doesn't match with id param
     */
    public void updateCreditCardById(String id, CreditCard creditCard) throws DataNotFoundException {
        CreditCard creditCardFound = creditCardRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find that Credit Card."));
        creditCardFound.setStatus(creditCard.getStatus());
        creditCardFound.setPin(creditCard.getPin());
        creditCardFound.setUserId(creditCard.getUserId());
        creditCardRepository.save(creditCardFound);
    }
    /**
     * This method found a match between a Account's id and param id then these user will be deleted.
     * @param id a integer value to find a exist Account
     * @throws DataNotFoundException if there isn't a Account whose id attribute doesn't match with id param
     */
    public void deleteCreditCardById(String id) throws DataNotFoundException {
        CreditCard creditCardFound = creditCardRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Could not find that credit card."));
        creditCardFound.setPin(null);
        creditCardFound.setUserId(null);
        creditCardFound.setStatus(null);
        creditCardRepository.delete(creditCardFound);
    }

    /**
     * This method get all Credit Cards match with user id param
     * @param userId a String value
     * @return All Credit Cards which was found
     * @throws CreditCardNotFoundException if there isn't any Credit Card whose user id doesn't matches userId param
     */
    public List<CreditCard> findByUserId(String userId) throws CreditCardNotFoundException {
        return creditCardRepository.findByUserId(userId);
    }
}
