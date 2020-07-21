package com.ironhack.userservice.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
    /**
     * Role's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Role's Role
     */
    private String role;

    /**
     * Role's user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userRole;

    /**
     * Default Constructor
     */
    public Role() {}

    /**
     * Constructor
     * @param role A String value
     */
    public Role(String role, User user) {
        this.role = role;
        this.userRole = user;
    }

    /**Getters & Setters**/

    /**
     * This method gets Role's id
     * @return a integer value
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets Role's id
     * @param id a long value
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *  This method gets Role's role
     * @return a Role element
     */
    public String getRole() {
        return role;
    }

    /**
     * This method sets Role's role
     * @param role A String value
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method gets Role's user
     * @return a User element
     */
    public User getUserRole() {
        return userRole;
    }

    /**
     * This method sets Role's user
     * @param user a User element
     */
    public void setUserRole(User user) {
        this.userRole = user;
    }

    /**
     * This method generate a string with a customize format.
     * @return a String value
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + role + "}"+ '\'';
    }

}
