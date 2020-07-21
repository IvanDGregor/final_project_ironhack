package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.classes.CreditCard;
import com.ironhack.edgeservice.model.classes.Transaction;
import com.ironhack.edgeservice.model.dto.PaymentDTO;
import com.ironhack.edgeservice.model.dto.TransferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name ="operations-service")
public interface OperationClient {
    @PutMapping("/transaction/transfer")
    public Transaction transfer(@RequestHeader(name = "Authorization") String token, @RequestBody TransferDTO transferDTO);
    @PutMapping("/transaction/payment")
    public Transaction payment(@RequestHeader(name = "Authorization") String token, @RequestBody PaymentDTO paymentDTO);
}
