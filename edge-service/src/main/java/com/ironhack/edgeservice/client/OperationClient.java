package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Transaction;
import com.ironhack.edgeservice.model.dto.PaymentDTO;
import com.ironhack.edgeservice.model.dto.TransferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="operations-service")
public interface OperationClient {
    @PutMapping("/transaction/transfer")
    public void transfer(@RequestHeader(name = "Authorization") String token, @RequestBody TransferDTO transferDTO);
    @PutMapping("/transaction/payment")
    public void payment(@RequestHeader(name = "Authorization") String token, @RequestBody PaymentDTO paymentDTO);
    @GetMapping("/transactions/{userId}")
    public List<Transaction> findAllByUserId(@RequestHeader(name = "Authorization") String token, @PathVariable String userId);
    @GetMapping("/transactions")
    public List<Transaction> findAll(@RequestHeader(name = "Authorization") String token);

}
