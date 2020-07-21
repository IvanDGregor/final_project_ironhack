package com.ironhack.edgeservice.model.dto;

import com.ironhack.edgeservice.model.enums.Status;

import java.math.BigDecimal;

public class PaymentDTO {

    private String creditCardId;
    private String pin;
    private BigDecimal amount;

    public PaymentDTO() {
    }

    public PaymentDTO(String creditCardId, String pin, BigDecimal amount) {
        this.creditCardId = creditCardId;
        this.pin = pin;
        this.amount = amount;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
