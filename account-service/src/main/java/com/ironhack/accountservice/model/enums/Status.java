package com.ironhack.accountservice.model.enums;

/**
 * Enum to define the Valid Types of Status Accounts
 */
public enum Status {
    ACTIVE("The default status of the account"),
    FROZEN("This state is activated when the system detects any anomaly");

    /**
     * Description of each Account Type
     */
    private String description;

    /**
     * Constructor
     *
     * @param description Receives the Account Type Description
     */
    Status(String description) {
        this.description = description;
    }

    /**
     * Getter of the Account Type Description
     *
     * @return Returns the Account Type Description
     */
    public String getDescription() {
        return description;
    }
}
