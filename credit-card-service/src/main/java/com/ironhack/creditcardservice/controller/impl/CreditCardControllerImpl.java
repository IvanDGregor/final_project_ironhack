package com.ironhack.creditcardservice.controller.impl;

import com.ironhack.creditcardservice.controller.interfaces.CreditCardControllerInterface;
import com.ironhack.creditcardservice.model.CreditCard;
import com.ironhack.creditcardservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardControllerImpl implements CreditCardControllerInterface {
    @Autowired
    private CreditCardService creditCardService;

    /**
     * Finds a List of Credit cards
     * @return Returns a list of all Credit cards.
     */
    @GetMapping("/credit-cards")
    @Override
    public List<CreditCard> findAll() {
        return creditCardService.findAll();
    }
    /**
     * Finds a Credit card by credit card id.
     * @param credit_card_id Receives the Account for searching by Param.
     * @return Returns a Account matching the given username.
     */
    @GetMapping("/credit-card/{credit_card_id}")
    @Override
    public CreditCard findById(@PathVariable(name = "credit_card_id") String credit_card_id) {
        return creditCardService.findById(credit_card_id);
    }

    /**
     * Creates a new Account.
     * @param creditCard Receives the Account Object by Body.
     * @return Returns the new Account.
     */
    @PostMapping("/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardService.createCreditCard(creditCard);
    }

    /**
     * Updates a Credit Card.
     * @param id Receives the Id of the Credit Card to update by Param.
     * @param creditCard Receives a Credit Card Object with the information to update by Body.
     */
    @PutMapping("/credit-card/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCreditCardById(@PathVariable String id, @RequestBody CreditCard creditCard){
        creditCardService.updateCreditCardById(id, creditCard);
    }

    /**
     * Deletes a Credit Card by Id.
     * @param id Receives the Id of the Credit Card to delete by Param.
     */
    @DeleteMapping("/credit-card/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreditCardById(@PathVariable String id) {
        creditCardService.deleteCreditCardById(id);
    }

}
