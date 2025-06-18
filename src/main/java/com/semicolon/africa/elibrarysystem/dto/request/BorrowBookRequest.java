package com.semicolon.africa.elibrarysystem.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class BorrowBookRequest {
    @NotBlank(message = "user_id require")
    private UUID userID;
    @NotBlank(message = "book_id require")
    private UUID bookID;

    public @NotBlank(message = "user_id require") UUID getUserID() {
        return userID;
    }

    public void setUserID(@NotBlank(message = "user_id require") UUID userID) {
        this.userID = userID;
    }

    public @NotBlank(message = "book_id require") UUID getBookID() {
        return bookID;
    }

    public void setBookID(@NotBlank(message = "book_id require") UUID bookID) {
        this.bookID = bookID;
    }
}
