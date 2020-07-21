package com.ironhack.userservice.model.classes;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    /**
     * User's id
     */
    @Id
    private String userId;
    /**
     * User's username
     */
    private String username;
    /**
     * User's password
     */
    private String password;
    /**
     * User's role
     */
    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy="userRole")
    private Set<Role> roles = new HashSet<>();
    /**
     * User's Date birth
     */
    private LocalDateTime date_birth;
    /**
     * User's surname
     */
    private String surname;

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Constructor Class
     * @param username a String value
     * @param password a String value
     * @param surname a String value
     * @param date_birth a LocalDateTime
     */
    public User(String userId, String username, String password, String surname, LocalDateTime date_birth) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.date_birth = date_birth;
    }

    // Getters & Setters

    /**
     * This method gets User's id
     * @return id (String)
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets User's id
     * @param id a long value
     */
    public void setUserId(String id) {
        this.userId = id;
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
     * This method gets User's roles
     * @return a Role's set
     */
    public Set<Role> getRole() {
        return roles;
    }

    /**
     * This method sets User's roles
     * @param roles a Role's element
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * This method gets User's date birth
     * @return a Role's set
     */
    public LocalDateTime getDate_birth() {
        return date_birth;
    }
    /**
     * This method sets User's date birth
     * @param date_birth a User's date birth
     */
    public void setDate_birth(LocalDateTime date_birth) {
        this.date_birth = date_birth;
    }
    /**
     * This method gets User's surname
     * @return a Surname User's set
     */
    public String getSurname() {
        return surname;
    }
    /**
     * This method sets User's surname
     * @param surname a User's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
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
}
