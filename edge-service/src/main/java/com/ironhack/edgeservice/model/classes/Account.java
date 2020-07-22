package com.ironhack.edgeservice.model.classes;

import com.ironhack.edgeservice.model.enums.Status;

import java.math.BigDecimal;

public class Account {
    /**
     * Account's Id
     */
    private String id;
    /**
     * Account's balance
     */
    private BigDecimal balance;

    /**
     * Account's Status
     */
    private Status status = Status.ACTIVE;

    /**
     * Account's Secret key
     */
    private String secretKey;

    /**
     * Account's user id
     */
    private String userId;

    /**
     * Void Constructor
     */
    public Account(){}

    /**
     * Constructor
     * @param id Receives the Account's id
     * @param secretKey Receives the Account's secret key
     * @param balance Receives the Account's balance
     * @param userId Receives the Account's user id associated to account
     * Status set ACTIVE for default
     */
    public Account(String id, String secretKey, BigDecimal balance, String userId) {
        this.id = id;
        this.secretKey = secretKey;
        this.userId = userId;
        this.balance = balance;
        this.status = Status.ACTIVE;
    }

    /**
     * Getter of Account's Id
     * @return Returns the Account's Id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of Account's Id
     * @param id Receives a Account 's Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter of Account's balance
     * @return Returns the Account's balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Setter of Account's balance
     * @param balance Receives a Account's balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Getter of Account's status
     * @return Returns the Account's status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * Setter of Account's status
     * @param status Receives a Account's status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Getter of Account's secret key
     * @return Returns the Account's secret key
     */
    public String getSecretKey() {
        return secretKey;
    }
    /**
     * Setter of Account's secret key
     * @param secretKey Receives a Account's secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    /**
     * Getter of Account's user id
     * @return Returns the Account's user id
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Setter of Account's user id
     * @param userId Receives a Account's user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}