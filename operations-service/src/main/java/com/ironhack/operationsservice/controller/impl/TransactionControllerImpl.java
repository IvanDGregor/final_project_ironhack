package com.ironhack.operationsservice.controller.impl;

import com.ironhack.operationsservice.model.classes.Transaction;
import com.ironhack.operationsservice.model.dto.PaymentDTO;
import com.ironhack.operationsservice.model.dto.TransferDTO;
import com.ironhack.operationsservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionControllerImpl {

    @Autowired
    private TransactionService transactionService;

    /**
     * Method for make a transfer.
     * @param transferDTO Receives the DTO object modified by Body.
     * @return Returns the transaction.
     */
    @PutMapping("/transaction/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Transaction transfer(@RequestBody TransferDTO transferDTO){
        return transactionService.transfer(transferDTO);
    }

    /**
     * Method for make a payment.
     * @param paymentDTO Receives the DTO object modified by Body.
     * @return Returns the transaction.
     */
    @PutMapping("/transaction/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Transaction payment(@RequestBody PaymentDTO paymentDTO){
        return transactionService.payment(paymentDTO);
    }
}
