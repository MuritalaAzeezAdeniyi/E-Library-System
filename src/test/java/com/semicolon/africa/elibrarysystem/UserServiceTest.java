package com.semicolon.africa.elibrarysystem;

import com.semicolon.africa.elibrarysystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.elibrarysystem.dto.response.RegisterUserResponse;
import com.semicolon.africa.elibrarysystem.exception.InvalidCredentialException;
import com.semicolon.africa.elibrarysystem.model.Role;
import com.semicolon.africa.elibrarysystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testThatUserCanRegister() throws InvalidCredentialException {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("admin22");
        registerUserRequest.setPassword("4444");
        registerUserRequest.setEmail("admin22@gmail.com");
        registerUserRequest.setRole(Role.ADMIN);
        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo("Successfully Registered!");
    }
}
