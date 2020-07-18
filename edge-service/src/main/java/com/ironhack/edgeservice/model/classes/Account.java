package com.ironhack.edgeservice.model.classes;

import com.ironhack.edgeservice.model.enums.Status;

import java.math.BigDecimal;

public class Account {
    /**
     * Account's Id
     */
    private Integer id;
    /**
     * Account's balance
     */
    private BigDecimal balance;

    /**
     * Account's Status
     */
    private Status status;

    /**
     * Account's Secret key
     */
    private String secret_key;

    /**
     * Void Constructor
     */
    public Account(){}

    /**
     * Constructor
     * @param secret_key Receives the Account's secret key
     */
    public Account(String secret_key) {
        this.secret_key = secret_key;
    }

    /**
     * Getter of Account's Id
     * @return Returns the Account's Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter of Account's Id
     * @param id Receives a Account 's Id
     */
    public void setId(Integer id) {
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
    public void setSBalance(BigDecimal balance) {
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
    public String getSecret_key() {
        return secret_key;
    }
    /**
     * Setter of Account's secret key
     * @param secret_key Receives a Account's secret key
     */
    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }
}