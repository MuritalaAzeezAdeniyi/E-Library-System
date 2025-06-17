package com.semicolon.africa.elibrarysystem.service.Implement;

import com.semicolon.africa.elibrarysystem.dto.request.LoginRequest;
import com.semicolon.africa.elibrarysystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.elibrarysystem.dto.response.RegisterUserResponse;
import com.semicolon.africa.elibrarysystem.exception.InvalidCredentialException;
import com.semicolon.africa.elibrarysystem.exception.UserAlreadyExistException;
import com.semicolon.africa.elibrarysystem.model.Users;
import com.semicolon.africa.elibrarysystem.repository.UsersRepo;
import com.semicolon.africa.elibrarysystem.service.JWTService;
import com.semicolon.africa.elibrarysystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    final private ModelMapper modelMapper;
    final private UsersRepo usersRepository;
    final private PasswordEncoder passwordEncoder;
    @Autowired
    JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserServiceImpl(ModelMapper modelMapper, UsersRepo usersRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws InvalidCredentialException {
        ValidateUserFields(registerUserRequest);
        ValidateUserEmail(registerUserRequest);
        Users user = modelMapper.map(registerUserRequest, Users.class);
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user = usersRepository.save(user);

        RegisterUserResponse response = modelMapper.map(user, RegisterUserResponse.class);
        response.setMessage("Successfully Registered");
        return response;
    }

    @Override

    public String login(LoginRequest request) throws InvalidCredentialException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(request.getUsername());
            }

            throw new InvalidCredentialException("Authentication failed");

        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialException("Invalid username or password");
        }
    }


    private void ValidateUserFields(RegisterUserRequest registerUserRequest) throws InvalidCredentialException {
        if (registerUserRequest.getUsername().trim().isEmpty() || registerUserRequest.getPassword().trim().isEmpty()
                || registerUserRequest.getEmail().trim().isEmpty()) {
            throw new InvalidCredentialException("Enter fields properly ");
        }
    }

    private void ValidateUserEmail(RegisterUserRequest registerUserRequest) throws InvalidCredentialException {
        Users user = usersRepository.findByEmail(registerUserRequest.getEmail());
        if (user != null) {
            throw new UserAlreadyExistException("Email already in use" + registerUserRequest.getUsername());
        }
    }
}
