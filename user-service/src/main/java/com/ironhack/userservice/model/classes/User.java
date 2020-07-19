package com.ironhack.userservice.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
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
    public User(String username, String password, String surname,LocalDateTime date_birth) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.date_birth = date_birth;
    }

    // Getters & Setters

    /**
     * This method gets User's id
     * @return id (Long)
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method sets User's id
     * @param id a long value
     */
    public void setId(Integer id) {
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
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + roles +
                '}';
    }
}
