package com.ironhack.operationsservice.model.dto;

import java.math.BigDecimal;

public class TransferDTO {

    private String accountSenderId;
    private String accountReceiverId;
    private BigDecimal amount;
    private String secret_key;

    public TransferDTO() {
    }

    public TransferDTO(String accountSenderId, String accountReceiverId, BigDecimal amount, String secret_key) {
        this.accountSenderId = accountSenderId;
        this.accountReceiverId = accountReceiverId;
        this.amount = amount;
        this.secret_key = secret_key;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }
}
