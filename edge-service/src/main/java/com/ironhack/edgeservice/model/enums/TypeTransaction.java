package com.ironhack.edgeservice.model.enums;

/**
 * Enum to define the Valid Types of Types of transactions
 */
public enum TypeTransaction {
    TRANSFER("If the transaction is a transfer"),
    CREDITCARD("If the transaction is a credit card payment");

    /**
     * Description of each Type Transaction
     */
    private String description;

    /**
     * Constructor
     *
     * @param description Receives the Type Description
     */
    TypeTransaction(String description) {
        this.description = description;
    }

    /**
     * Getter of the Type Description
     *
     * @return Returns the Type Description
     */
    public String getDescription() {
        return description;
    }
}
