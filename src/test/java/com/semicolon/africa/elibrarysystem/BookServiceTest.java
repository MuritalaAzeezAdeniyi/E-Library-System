package com.semicolon.africa.elibrarysystem;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
       borrowBookRequest.setUserID(c36f4b52-a143-4494-8b85-dd678b4c651c);
       borrowBookRequest.setBookID(ca092631-517b-479f-9000-24e2ceafb19b);


    }
}
