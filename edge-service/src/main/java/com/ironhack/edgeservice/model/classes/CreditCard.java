package com.ironhack.edgeservice.model.classes;

import com.ironhack.edgeservice.model.enums.Status;

public class CreditCard {

    /**
     * Credit card's Id
     */
    private String id;
    /**
     * Credit card's balance
     */
    private String pin;
    /**
     * Credit card's user id
     */
    private String userId;
    /**
     * Credit card's user id
     */
    private Status status = Status.ACTIVE;

    /**
     * Void Constructor
     */
    public CreditCard(){}

    /**
     * Constructor
     * @param pin Receives the Account's secret key
     * @param userId Receives the Credit card's user id associated to account
     * Status set ACTIVE for default
     */
    public CreditCard(String id, String pin, String userId) {
        this.id = id;
        this.pin = pin;
        this.userId = userId;
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
     * Getter of Credit Card's PIN
     * @return Returns the Credit Card's PIN
     */
    public String getPin() {
        return pin;
    }
    /**
     * Setter of Credit Card's PIN
     * @param pin Receives a Credit Card's PIN
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
    /**
     * Getter of Credit Card's User id
     * @return Returns the Credit Card's User id
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Setter of Credit Card's User id
     * @param userId Receives a Credit Card's User id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Getter of Credit Card's Status
     * @return Returns the Credit Card's Status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * Setter of Credit Card's Status
     * @param status Receives a Credit Card's Status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
