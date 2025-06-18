package com.semicolon.africa.elibrarysystem.controller;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.dto.response.ApiResponse;
import com.semicolon.africa.elibrarysystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest addBookRequest) {
        try{
            AddBookResponse addBookResponse = bookService.addBook(addBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,addBookResponse), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody @Valid BorrowBookRequest borrowBookRequest) {
        try{
            String response = bookService.borrowBook(borrowBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(UUID recordId){
        try{
            String response = bookService.returnBook(recordId);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(UUID bookId){
        try{
            String response = bookService.deleteBook(bookId);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
