package com.ironhack.creditcardservice.model;

import com.ironhack.creditcardservice.model.enums.Status;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

/** Entity Credit Card is related a register which the user information **/
@Entity
@Table(name = "credit_card")
@DynamicUpdate
public class CreditCard {
    /**
     * Credit Card's Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Credit Card's Status
     */
    @Enumerated(EnumType.STRING)
    private Status status;
    /**
     * Credit Card's pin
     */
    private String pin;
    /**
     * Credit Card's user_id
     */
    private Integer user_id;

    /**
     * Void Constructor
     */
    public CreditCard(){}

    /**
     * Constructor
     * @param pin Receives the Credit Card's pin
     * @param user_id Receives the Credit Card's user id associated to credit card
     * Status set ACTIVE for default
     */
    public CreditCard(String pin, Integer user_id) {
        this.pin = pin;
        this.user_id = user_id;
        this.status = Status.ACTIVE;
    }
    /**
     * Getter of Credit Card's Id
     * @return Returns the Credit Card's Id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Setter of Credit Card's Id
     * @param id Receives a Credit Card's Id
     */
    public void setId(Integer id) {
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
    public Integer getUser_id() {
        return user_id;
    }
    /**
     * Setter of Credit Card's user id
     * @param user_id Receives a Credit Card's user id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
