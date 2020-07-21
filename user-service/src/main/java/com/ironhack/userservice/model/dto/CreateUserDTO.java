package com.ironhack.userservice.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateUserDTO {

    @NotNull(message = "ID must not be null")
    private String userId;
    @NotNull(message = "Username must not be null")
    private String username;
    @NotNull(message = "Surname must not be null")
    private String surname;
    @NotNull(message = "Password type must not be null")
    private String password;
    @NotNull(message = "Date Birth type must not be null")
    private LocalDateTime date_birth;
    @NotEmpty(message = "Role must not be null")
    private String role;

    public CreateUserDTO(){}

    public CreateUserDTO(@NotNull(message = "ID must not be null") String userId, @NotNull(message = "Username must not be null") String username, @NotNull(message = "Surname must not be null") String surname, @NotNull(message = "Password type must not be null") String password, @NotNull(message = "Date Birth type must not be null") LocalDateTime date_birth, @NotEmpty(message = "Role must not be null") String role) {
        this.userId = userId;
        this.username = username;
        this.surname = surname;
        this.password = password;
        this.date_birth = date_birth;
        this.role = role;
    }

    public CreateUserDTO(@NotNull(message = "Username must not be null") String username, @NotNull(message = "Surname must not be null") String surname, @NotNull(message = "Password type must not be null") String password, @NotNull(message = "Date Birth type must not be null") LocalDateTime date_birth, @NotEmpty(message = "Role must not be null") String role) {
        this.username = username;
        this.surname = surname;
        this.password = password;
        this.date_birth = date_birth;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(LocalDateTime date_birth) {
        this.date_birth = date_birth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
