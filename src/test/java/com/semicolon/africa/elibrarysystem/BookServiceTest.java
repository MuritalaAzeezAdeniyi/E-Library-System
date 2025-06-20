package com.semicolon.africa.elibrarysystem;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.UpdateBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.model.Book;
import com.semicolon.africa.elibrarysystem.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Test
    public void testThatAdminCanAddBook(){
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setAuthor("John Doe");
        addBookRequest.setTitle("Java");
        addBookRequest.setQuantity(1);
        AddBookResponse addBookResponse = bookService.addBook(addBookRequest);
        assertThat(addBookResponse).isNotNull();

    }
    @Test
    public void testThatUserCanBorrowBook(){
       BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
       borrowBookRequest.setUserID(UUID.fromString("c36f4b52-a143-4494-8b85-dd678b4c651c"));
       borrowBookRequest.setBookID(UUID.fromString("ca092631-517b-479f-9000-24e2ceafb19b"));
       String response = bookService.borrowBook(borrowBookRequest);
       assertThat(response).isNotNull();


    }

    @Test
    public void testThatUserCanReturnBook(){
        UUID user = UUID.fromString("2e8ea5ec-20c4-4831-bc4c-4338dc7c025e");
        String response = bookService.returnBook(user);
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatAdminCanDeleteBook(){
       UUID bookId = UUID.fromString("ca092631-517b-479f-9000-24e2ceafb19b");
       String response = bookService.deleteBook(bookId);
       assertThat(response).isNotNull();
    }@Test
    public void tesThatAdminCanUpdateBook(){
        UUID bookId = UUID.fromString("072a3e7f-52b5-490b-a2b4-c2ab83eda5b6");
       UpdateBookRequest updateBookRequest = new UpdateBookRequest();
       updateBookRequest.setId(bookId);
       updateBookRequest.setAuthor("Doe");
       updateBookRequest.setTitle("Python");
       String response = bookService.UpdateBook(updateBookRequest);
       assertThat(response).isNotNull();
    }
    @Test
    public void tesThatAdminCanViewBook(){
        UUID bookId = UUID.fromString("072a3e7f-52b5-490b-a2b4-c2ab83eda5b6");

        Book response = bookService.viewBookById(bookId);
        System.out.println(response);
        assertThat(response).isNotNull();
        Assertions.assertEquals("Java",response.getTitle());


    }

}
