package com.semicolon.africa.elibrarysystem.service;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.UpdateBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    AddBookResponse addBook (AddBookRequest addBookRequest);
    String borrowBook (BorrowBookRequest borrowBookRequest);
    String returnBook (UUID id);
    String deleteBook (UUID id);
    String UpdateBook (UpdateBookRequest updateBookRequest);
    List<Book> getAllBooks();
    Book viewBookById (UUID id);
}
