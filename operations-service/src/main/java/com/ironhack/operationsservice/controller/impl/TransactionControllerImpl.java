package com.ironhack.operationsservice.controller.impl;

import com.ironhack.operationsservice.model.classes.Transaction;
import com.ironhack.operationsservice.model.dto.PaymentDTO;
import com.ironhack.operationsservice.model.dto.TransferDTO;
import com.ironhack.operationsservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionControllerImpl {

    @Autowired
    private TransactionService transactionService;

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
     * @param userId Receives the string for the User Id
     * @return Returns all Transaction by User Id.
     */
    @GetMapping("/transactions/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findAllByUserId(@PathVariable String userId){
        return transactionService.findAllByUserId(userId);
    }

    /**
     * Finds a all Transaction by User Id.
     * @return Returns all Transaction by User Id.
     */
    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }
}
