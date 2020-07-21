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

@Api(tags = "Credit Card Controller")
@RestController
@RequestMapping("/")
public class TransactionController {

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
