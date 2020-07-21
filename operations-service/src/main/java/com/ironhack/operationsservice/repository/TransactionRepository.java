package com.ironhack.operationsservice.repository;

import com.ironhack.operationsservice.model.classes.Account;
import com.ironhack.operationsservice.model.classes.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, String> {
}
