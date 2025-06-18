package com.semicolon.africa.elibrarysystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;
@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean  returned;
   @ManyToOne
   @JoinColumn(name = "user_id")
    private Users user;

   @ManyToOne
   @JoinColumn(name = "book_id")
   private Book book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
