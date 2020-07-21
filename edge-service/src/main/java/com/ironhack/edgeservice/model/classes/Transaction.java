package com.ironhack.edgeservice.model.classes;

import com.ironhack.edgeservice.model.enums.TypeTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String id;

    private String accountSenderId;

    private String accountReceiverId;

    private String userId;

    private BigDecimal amount;

    private LocalDateTime date;

    private TypeTransaction typeTransaction;

    public Transaction() {
    }

    public Transaction(String accountSenderId, String accountReceiverId, String userId, BigDecimal amount, LocalDateTime date, TypeTransaction typeTransaction) {
        this.accountSenderId = accountSenderId;
        this.accountReceiverId = accountReceiverId;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.typeTransaction = typeTransaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountSenderId() {
        return accountSenderId;
    }

    public void setAccountSenderId(String accountSenderId) {
        this.accountSenderId = accountSenderId;
    }

    public String getAccountReceiverId() {
        return accountReceiverId;
    }

    public void setAccountReceiverId(String accountReceiverId) {
        this.accountReceiverId = accountReceiverId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
}

