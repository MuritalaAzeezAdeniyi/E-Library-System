package com.semicolon.africa.elibrarysystem.dto.response;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
