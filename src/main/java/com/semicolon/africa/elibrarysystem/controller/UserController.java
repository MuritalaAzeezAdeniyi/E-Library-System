package com.semicolon.africa.elibrarysystem.controller;

import com.semicolon.africa.elibrarysystem.dto.request.LoginRequest;
import com.semicolon.africa.elibrarysystem.dto.request.RegisterUserRequest;
import com.semicolon.africa.elibrarysystem.dto.response.ApiResponse;
import com.semicolon.africa.elibrarysystem.dto.response.RegisterUserResponse;
import com.semicolon.africa.elibrarysystem.service.Implement.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser( @RequestBody @Valid RegisterUserRequest registerUserRequest) {
       try{
           RegisterUserResponse response = userService.registerUser(registerUserRequest);
           return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Valid LoginRequest loginRequest) {
        try{
            String response = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
