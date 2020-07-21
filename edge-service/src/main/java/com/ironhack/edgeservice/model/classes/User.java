package com.ironhack.edgeservice.model.classes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    /**
     * Attributes
     */
    private String userId;
    private String username;
    private String surname;
    private LocalDateTime date_birth;
    private String password;
    private Set<Role> roles = new HashSet<>();

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Constructor Class
     * @param userId a String value
     * @param username a String value
     * @param password a String value
     * @param surname a String value
     * @param date_birth a LocalDateTime value
     */
    public User(String userId, String username, String password, String surname, LocalDateTime date_birth) {
        this.userId = userId;
        this.username = username;
        this.surname = surname;
        this.date_birth = date_birth;
        this.password = password;
    }
    // Getters & Setters

    /**
     * This method gets User's id
     * @return id (Long)
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets User's id
     * @param userId a long value
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method gets User's name
     * @return name  (string)
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets User's name
     * @param username A String value
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *  This method gets User's password
     * @return a password (string)
     */
    public String getPassword() {
        return password;
    }

    /**
     *  This method sets User's password
     * @param password a password element
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method gets User's roles
     * @return a Role's set
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * This method sets User's roles
     * @param roles a Rolse element
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(LocalDateTime date_birth) {
        this.date_birth = date_birth;
    }

    /**
     * This method prints user with his properties
     *  @return A customize String format.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

}
