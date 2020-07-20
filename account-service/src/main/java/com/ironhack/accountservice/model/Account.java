package com.ironhack.accountservice.model;

import com.ironhack.accountservice.model.enums.Status;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/** Entity Account is related a register which the user information **/
@Entity
@Table(name = "accounts")
@DynamicUpdate
public class Account {
    /**
     * Account's Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
     * Account's User id associated
     */
    private Integer user_id;

    /**
     * Void Constructor
     */
    public Account(){}

    /**
     * Constructor
     * @param secret_key Receives the Account's secret key
     * @param balance Receives the Account's balance
     * @param user_id Receives the Account's user id associated to account
     * Status set ACTIVE for default
     */
    public Account(String secret_key, BigDecimal balance, Integer user_id) {
        this.secret_key = secret_key;
        this.user_id = user_id;
        this.balance = balance;
        setStatus(Status.ACTIVE);
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
     * @param id Receives a Account's Id
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
    public Integer getUser_id() {
        return user_id;
    }
    /**
     * Setter of Account's user id
     * @param user_id Receives a Account's user id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}