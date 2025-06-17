package com.semicolon.africa.elibrarysystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class Book {
   @Id
   @GeneratedValue
    private UUID id;
    private String title;
    private String author;
    private String publishedYear;
}
