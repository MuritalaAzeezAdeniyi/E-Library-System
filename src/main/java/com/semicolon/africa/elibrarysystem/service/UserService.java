package com.semicolon.africa.elibrarysystem.service;

import com.semicolon.africa.elibrarysystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.elibrarysystem.dto.response.RegisterUserResponse;
import com.semicolon.africa.elibrarysystem.exception.InvalidCredentialException;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws InvalidCredentialException;
}
