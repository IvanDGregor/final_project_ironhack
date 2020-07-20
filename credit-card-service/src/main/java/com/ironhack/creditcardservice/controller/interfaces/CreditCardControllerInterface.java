package com.ironhack.creditcardservice.controller.interfaces;

import com.ironhack.creditcardservice.model.CreditCard;

import java.util.List;

public interface CreditCardControllerInterface {
    public List<CreditCard> findAll();
    public CreditCard findById(Integer credit_card_id);
    public CreditCard createCreditCard(CreditCard creditCard);
    public void updateCreditCardById(Integer id, CreditCard creditCard);
    public void deleteCreditCardById(Integer id);
}