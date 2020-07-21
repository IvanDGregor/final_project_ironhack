package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.service.CreditCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Credit Card Controller")
@RestController
@RequestMapping("/")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    /**
     * Finds a all Credit Cards.
     * @return Returns all Credit Cards
     */
    @GetMapping("/credit-cards")
    @ApiOperation(value="Find All Credit Cards",
            notes = "Lists all Credit Cards created",
            response = CreditCard.class)
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> findAll(){
        return creditCardService.findAll();
    }
    /**
     * Finds a Credit Card by credit card id.
     * @param credit_card_id Receives the Credit Card Id for searching by Param.
     * @return Returns a Credit Card matching the given credit card id.
     */
    @GetMapping("/credit-card/{credit_card_id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard findById(@PathVariable(name = "credit_card_id") Integer credit_card_id) {
        return creditCardService.findById(credit_card_id);
    }

    /**
     * Create new Credit Card
     * @param creditCard Receives Credit Card object to create Credit Card
     * @return Returns a new Credit Card created
     */
    @PostMapping("/credit-card")
    @ApiOperation(value="Create New Credit Card",
            response = CreditCard.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createAccount(@RequestBody CreditCard creditCard) {
        return creditCardService.createCreditCard(creditCard);
    }

    /**
     * Update Credit Card
     * @param creditCard Receives Credit Card object to update Credit Card
     * @param credit_card_id Receives Credit Card id to update Credit Card
     * @return Returns a new Credit Card created
     */
    @PutMapping("/credit-card/{credit_card_id}")
    @ApiOperation(value="Update Credit Card",
            response = Account.class)
    @ResponseStatus(HttpStatus.OK)
    public CreditCard updateCreditCard(@PathVariable(name = "credit_card_id") Integer credit_card_id, @RequestBody CreditCard creditCard){
        return creditCardService.updateCreditCard(credit_card_id, creditCard);
    }

    /**
     * Delete Credit Card
     * @param credit_card_id Receives Credit Card id to delete Credit Card
     * @return Returns a new Credit Card created
     */
    @DeleteMapping("/credit-card/{credit_card_id}")
    @ApiOperation(value="Delete Credit Card",
            response = CreditCard.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(name = "credit_card_id") Integer credit_card_id){
        creditCardService.deleteCreditCard(credit_card_id);
    }
}
