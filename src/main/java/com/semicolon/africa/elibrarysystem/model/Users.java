package com.semicolon.africa.elibrarysystem.model;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
public class Users {
    @Id
    @GeneratedValue
    private UUID userId;
    private String username;
    private String password;
    private String email;
   @Enumerated(EnumType.STRING)
    private Role role;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
