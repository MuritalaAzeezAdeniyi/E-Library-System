package com.semicolon.africa.elibrarysystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class BorrowBookRequest {
    @NotNull(message = "user_id require")
    private UUID userID;
    @NotNull(message = "book_id require")
    private UUID bookID;

    public @NotNull(message = "user_id require") UUID getUserID() {
        return userID;
    }

    public void setUserID(@NotNull(message = "user_id require") UUID userID) {
        this.userID = userID;
    }

    public @NotNull(message = "book_id require") UUID getBookID() {
        return bookID;
    }

    public void setBookID(@NotNull(message = "book_id require") UUID bookID) {
        this.bookID = bookID;
    }
}
