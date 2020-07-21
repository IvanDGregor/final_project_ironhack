package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.AccountClient;
import com.ironhack.edgeservice.client.OperationClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exception.AccountClientNotWorkingException;
import com.ironhack.edgeservice.exception.AccountNotFoundException;
import com.ironhack.edgeservice.exception.OperationsClientNotWorkingException;
import com.ironhack.edgeservice.model.classes.Transaction;
import com.ironhack.edgeservice.model.dto.PaymentDTO;
import com.ironhack.edgeservice.model.dto.TransferDTO;
import com.ironhack.edgeservice.util.JwtUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    OperationClient operationClient;
    @Autowired
    UserClient userClient;
    @Autowired
    JwtUtil jwtUtil;

    /**
     * This make a transfer
     * @param transferDTO a object to make transfer
     */
    @HystrixCommand(fallbackMethod = "transferNotAvailable")
    public Transaction transfer(TransferDTO transferDTO) {
        String operationToken = "Bearer " + jwtUtil.generateToken("operations-service");
        return operationClient.transfer(operationToken, transferDTO);
    }

    public Transaction transferNotAvailable(TransferDTO transferDTO) {
        throw new OperationsClientNotWorkingException("operations-service not available!");
    }

    /**
     * This method make a payment
     * @param paymentDTO a Object to make a payment
     */
    @HystrixCommand(fallbackMethod = "paymentNotAvailable")
    public Transaction payment(PaymentDTO paymentDTO) {
        String operationToken = "Bearer " + jwtUtil.generateToken("operations-service");
        return operationClient.payment(operationToken, paymentDTO);
    }

    public Transaction paymentNotAvailable(PaymentDTO paymentDTO) {
        throw new OperationsClientNotWorkingException("operations-service not available!");
    }


}
