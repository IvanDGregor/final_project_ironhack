package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.CreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="credit-card-service")
public interface CreditCardClient {
    @GetMapping("/credit-cards")
    public List<CreditCard> findAll(@RequestHeader(name = "Authorization") String token);
    @GetMapping("/credit-card/{card_id}")
    public CreditCard findById(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "card_id") String card_id);
    @GetMapping("/credit-card/user/{user_id}")
    public List<CreditCard> findByUserId(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "user_id") String user_id);
    @PostMapping("/credit-card")
    public CreditCard createCreditCard(@RequestHeader(name = "Authorization") String token, @RequestBody CreditCard creditCard);
    @PutMapping("/credit-card/{card_id}")
    public void updateCreditCard(@RequestHeader(name = "Authorization") String token, @PathVariable String card_id, @RequestBody CreditCard creditCard);
    @DeleteMapping("/credit-card/{card_id}")
    public void deleteCreditCard(@RequestHeader(name = "Authorization") String token, @PathVariable String card_id);
}
