package com.ironhack.creditcardservice.controller.interfaces;

import com.ironhack.creditcardservice.model.classes.CreditCard;

import java.util.List;

public interface CreditCardControllerInterface {
    public List<CreditCard> findAll();
    public CreditCard findById(String credit_card_id);
    public CreditCard createCreditCard(CreditCard creditCard);
    public void updateCreditCardById(String id, CreditCard creditCard);
    public void deleteCreditCardById(String id);
}
