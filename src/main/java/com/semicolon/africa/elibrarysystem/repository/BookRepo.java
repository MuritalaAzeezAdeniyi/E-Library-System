package com.semicolon.africa.elibrarysystem.repository;

import com.semicolon.africa.elibrarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepo extends JpaRepository<Book, UUID> {
    Optional<Book> findByAuthorAndTitle(String author, String title);
     Optional <Book> findByBookId(UUID bookId);
}
