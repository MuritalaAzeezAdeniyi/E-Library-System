package com.semicolon.africa.elibrarysystem.service.Implement;

import com.semicolon.africa.elibrarysystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.elibrarysystem.dto.response.RegisterUserResponse;
import com.semicolon.africa.elibrarysystem.exception.InvalidCredentialException;
import com.semicolon.africa.elibrarysystem.exception.UserAlreadyExistException;
import com.semicolon.africa.elibrarysystem.model.Users;
import com.semicolon.africa.elibrarysystem.repository.UsersRepo;
import com.semicolon.africa.elibrarysystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
   final private ModelMapper modelMapper;
   final private UsersRepo usersRepository;
   final private PasswordEncoder passwordEncoder;

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
        usersRepository.save(user);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setEmail(registerUserRequest.getEmail());
        registerUserResponse.setEmail("Successfully Registered!");
        return registerUserResponse;
    }



    private void ValidateUserFields(RegisterUserRequest registerUserRequest) throws InvalidCredentialException {
        if (registerUserRequest.getUsername().trim().isEmpty()|| registerUserRequest.getPassword().trim().isEmpty()
                || registerUserRequest.getEmail().trim().isEmpty()) {
            throw new InvalidCredentialException("Enter fields properly ");
        }
    }

    private void ValidateUserEmail(RegisterUserRequest registerUserRequest) throws InvalidCredentialException {
        Users user = usersRepository.findByEmail(registerUserRequest.getEmail());
        if(user != null){
            throw new UserAlreadyExistException("Email already in use" + registerUserRequest.getUsername());
        }
    }
}
