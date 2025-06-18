package com.semicolon.africa.elibrarysystem.model;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
public class Book {
   @Id
   @GeneratedValue
    private UUID bookId;

    private String title;
    private String author;
    private int totalCopy;
    private int availableCopy;


    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCopy() {
        return totalCopy;
    }

    public void setTotalCopy(int totalCopy) {
        this.totalCopy = totalCopy;
    }

    public int getAvailableCopy() {
        return availableCopy;
    }

    public void setAvailableCopy(int availableCopy) {
        this.availableCopy = availableCopy;
    }
}
