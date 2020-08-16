package com.ironhack.creditcardservice.repository;

import com.ironhack.creditcardservice.model.classes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
    List<CreditCard> findByUserId(String userId);
}
