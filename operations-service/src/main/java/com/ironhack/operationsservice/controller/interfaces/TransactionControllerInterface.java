package com.ironhack.operationsservice.controller.interfaces;

import com.ironhack.operationsservice.model.classes.Transaction;
import com.ironhack.operationsservice.model.dto.PaymentDTO;
import com.ironhack.operationsservice.model.dto.TransferDTO;

import java.util.List;

public interface TransactionControllerInterface {
    public void transfer(TransferDTO transferDTO);
    public void payment(PaymentDTO paymentDTO);
    public List<Transaction> findAllByUserId(String userId);
    public List<Transaction> findAll();
}
