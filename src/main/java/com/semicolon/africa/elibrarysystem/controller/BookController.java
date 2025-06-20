package com.semicolon.africa.elibrarysystem.controller;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.UpdateBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.dto.response.ApiResponse;
import com.semicolon.africa.elibrarysystem.model.Book;
import com.semicolon.africa.elibrarysystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest addBookRequest) {
        try {
            AddBookResponse addBookResponse = bookService.addBook(addBookRequest);
            return new ResponseEntity<>(new ApiResponse(true, addBookResponse), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody @Valid BorrowBookRequest borrowBookRequest) {
        try {
            String response = bookService.borrowBook(borrowBookRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/returnBook/{recordId}")
    public ResponseEntity<?> returnBook(@PathVariable UUID recordId) {
        try {
            String response = bookService.returnBook(recordId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID bookId) {
        try {
            String response = bookService.deleteBook(bookId);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody @Valid UpdateBookRequest updateBookRequest) {
        try {
            String response = bookService.UpdateBook(updateBookRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewAllBooks")
    public ResponseEntity<?> getAllBooks() {
        try {
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewBookById/{bookId}")
    public ResponseEntity<?> viewBookById(@PathVariable UUID bookId) {
        try {
            Book book = bookService.viewBookById(bookId);
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
