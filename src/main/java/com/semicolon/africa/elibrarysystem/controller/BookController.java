package com.semicolon.africa.elibrarysystem.controller;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.UpdateBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.dto.response.ApiResponse;
import com.semicolon.africa.elibrarysystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest addBookRequest) {
        try{
            AddBookResponse addBookResponse = bookService.addBook(addBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,addBookResponse), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody @Valid BorrowBookRequest borrowBookRequest) {
        try{
            String response = bookService.borrowBook(borrowBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(UUID recordId){
        try{
            String response = bookService.returnBook(recordId);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('AMIN')")
    @PostMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(UUID bookId){
        try{
            String response = bookService.deleteBook(bookId);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody @Valid UpdateBookRequest updateBookRequest){
        try{
            String response = bookService.UpdateBook(updateBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewAllBooks")
    public ResponseEntity<?> getAllBooks(){
        try{
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
