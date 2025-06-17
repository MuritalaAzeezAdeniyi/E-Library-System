package com.semicolon.africa.elibrarysystem.dto.request;

import com.semicolon.africa.elibrarysystem.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterUserRequest {
    @NotBlank(message = " username is require ")
    private String username;

    @NotBlank(message = " password is require ")
    private String password;

    @NotBlank(message = " email is require ")
    @Email(message = " invalid email format ")
    private String email;

    @NotNull(message = " role is require ")
    @Enumerated(EnumType.STRING)
    private Role role;

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
