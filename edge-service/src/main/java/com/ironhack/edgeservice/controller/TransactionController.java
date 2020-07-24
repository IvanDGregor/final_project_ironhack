package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Transaction;
import com.ironhack.edgeservice.model.dto.PaymentDTO;
import com.ironhack.edgeservice.model.dto.TransferDTO;
import com.ironhack.edgeservice.service.CreditCardService;
import com.ironhack.edgeservice.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Credit Card Controller")
@RestController
@RequestMapping("/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Finds a all Transaction by User Id.
     * @param userId Receives the string for the User Id
     * @return Returns all Transaction by User Id.
     */
    @GetMapping("/transactions/{userId}")
    @ApiOperation(value="Find All Transaction by User Id",
            notes = "Find All Transaction by User Id",
            response = Transaction.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findAllByUserId(@PathVariable String userId){
        return transactionService.findAllByUserId(userId);
    }
    /**
     * Method for make a transfer.
     * @param transferDTO Receives the DTO object modified by Body.
     */
    @PutMapping("/transaction/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@RequestBody TransferDTO transferDTO){
        transactionService.transfer(transferDTO);
    }

    /**
     * Method for make a payment.
     * @param paymentDTO Receives the DTO object modified by Body.
     */
    @PutMapping("/transaction/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void payment(@RequestBody PaymentDTO paymentDTO){
        transactionService.payment(paymentDTO);
    }

    /**
     * Finds a all Transaction by User Id.
     * @return Returns all Transaction by User Id.
     */
    @GetMapping("/transactions")
    @ApiOperation(value="Find All Transactions",
            notes = "Find All Transactions",
            response = Transaction.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }
}
