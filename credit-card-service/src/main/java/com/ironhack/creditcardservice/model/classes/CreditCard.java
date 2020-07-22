package com.ironhack.creditcardservice.model.classes;

import com.ironhack.creditcardservice.model.enums.Status;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/** Entity Credit Card is related a register which the user information **/
@Entity
@Table(name = "credit_card")
@DynamicUpdate
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
    private Status status;
    /**
     * Credit Card's pin
     */
    private String pin;
    /**
     * Credit Card's user_id
     */
    private String user_id;

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
    public CreditCard(String id, String pin, String user_id) {
        this.id = id;
        this.pin = pin;
        this.user_id = user_id;
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
    public String getUser_id() {
        return user_id;
    }
    /**
     * Setter of Credit Card's user id
     * @param user_id Receives a Credit Card's user id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
