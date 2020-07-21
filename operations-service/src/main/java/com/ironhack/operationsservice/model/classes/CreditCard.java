package com.ironhack.operationsservice.model.classes;

import com.ironhack.operationsservice.model.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class CreditCard {
    /**
     * Credit Card's Id
     */
    @Id
    private String id;
    /**
     * Credit Card's Status
     */
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    /**
     * Credit Card's pin
     */
    private String pin;
    /**
     * Credit Card's user_id
     */
    private String userId;
    /**
     * Credit Card's accountId
     */
    private String accountId;

    /**
     * Void Constructor
     */
    public CreditCard(){}

    /**
     * Constructor
     * @param pin Receives the Credit Card's pin
     * @param userId Receives the Credit Card's user id associated to credit card
     * Status set ACTIVE for default
     */
    public CreditCard(String pin, String userId, String accountId) {
        this.pin = pin;
        this.userId = userId;
        this.accountId = accountId;
        this.status = Status.ACTIVE;
    }

    /**
     * Getter of Credit Card's Id
     * @return Returns the Credit Card's Id
     */
    public String getId() {
        return id;
    }
    /**
     * Setter of Credit Card's Id
     * @param id Receives a Credit Card's Id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Getter of Credit Card's Status
     * @return Returns the Credit Card's Status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * Setter of Credit Card's status
     * @param status Receives a Credit Card's status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Getter of Credit Card's pin
     * @return Returns the Credit Card's pin
     */
    public String getPin() {
        return pin;
    }
    /**
     * Setter of Credit Card's pin
     * @param pin Receives a Credit Card's pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
    /**
     * Getter of Credit Card's user id
     * @return Returns the Credit Card's user id
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Setter of Credit Card's user id
     * @param userId Receives a Credit Card's user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Getter of Credit Card's account id
     * @return Returns the Credit Card's account id
     */
    public String getAccountId() {
        return accountId;
    }
    /**
     * Setter of Credit Card's account id
     * @param accountId Receives a Credit Card's account id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
