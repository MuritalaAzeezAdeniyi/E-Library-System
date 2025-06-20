package com.semicolon.africa.elibrarysystem.service;

import com.semicolon.africa.elibrarysystem.dto.request.LoginRequest;
import com.semicolon.africa.elibrarysystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.elibrarysystem.dto.response.LoginResponse;
import com.semicolon.africa.elibrarysystem.dto.response.RegisterUserResponse;
import com.semicolon.africa.elibrarysystem.exception.InvalidCredentialException;

import java.util.UUID;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws InvalidCredentialException;
    String login(LoginRequest loginRequest) throws InvalidCredentialException;
    String DeleteUserAccount(UUID userId) throws InvalidCredentialException;
}
