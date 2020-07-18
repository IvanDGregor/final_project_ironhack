package com.ironhack.userservice.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    /**
     * User's id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * User's username
     */
    @Column(unique = true)
    private String username;
    /**
     * User's password
     */
    private String password;
    /**
     * User's role
     */
    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy="user")
    private Set<Role> roles = new HashSet<>();
    /**
     * User's account_id
     */
    private Long account_id;

    /**
     * Default Constructor
     */
    public User() {}

    /**
     * Constructor Class
     * @param username a String value
     * @param password a String value
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters & Setters

    /**
     * This method gets User's id
     * @return id (Long)
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets User's id
     * @param id a long value
     */
    public void setId(Long id) {
        this.id = id;
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
     * @param roles a Role's element
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * This method gets User's roles
     * @return a Role's set
     */
    public Long getAccount_id() {
        return account_id;
    }
    /**
     * This method sets User's account id
     * @param account_id a Role's account id
     */
    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    /**
     * This method prints user with his properties
     *  @return A customize String format.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + roles +
                '}';
    }
}
