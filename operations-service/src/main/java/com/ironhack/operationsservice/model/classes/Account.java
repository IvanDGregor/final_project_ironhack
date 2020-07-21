package com.ironhack.operationsservice.model.classes;

import com.ironhack.operationsservice.model.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "accounts")
public class Account {
    /**
     * Account's Id
     */
    @Id
    private String id;
    /**
     * Account's balance
     */
    private BigDecimal balance;

    /**
     * Account's Status
     */
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    /**
     * Account's Secret key
     */
    private String secret_key;

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
     * @param secret_key Receives the Account's secret key
     * @param balance Receives the Account's balance
     * @param userId Receives the Account's user id associated to account
     * Status set ACTIVE for default
     */
    public Account(String secret_key, BigDecimal balance, String userId) {
        this.secret_key = secret_key;
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