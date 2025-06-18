package com.semicolon.africa.elibrarysystem.service.Implement;

import com.semicolon.africa.elibrarysystem.dto.request.AddBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.BorrowBookRequest;
import com.semicolon.africa.elibrarysystem.dto.request.UpdateBookRequest;
import com.semicolon.africa.elibrarysystem.dto.response.AddBookResponse;
import com.semicolon.africa.elibrarysystem.model.Book;
import com.semicolon.africa.elibrarysystem.model.BorrowRecord;
import com.semicolon.africa.elibrarysystem.model.Users;
import com.semicolon.africa.elibrarysystem.repository.BookRepo;
import com.semicolon.africa.elibrarysystem.repository.BorrowRecordRepo;
import com.semicolon.africa.elibrarysystem.repository.UsersRepo;
import com.semicolon.africa.elibrarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private BorrowRecordRepo borrowRepo;

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        Optional<Book> existing = bookRepo.findByAuthorAndTitle(addBookRequest.getAuthor(), addBookRequest.getTitle());
        if (existing.isPresent()) {
            Book book = existing.get();
            book.setTotalCopy(book.getTotalCopy() + addBookRequest.getQuantity());
            book.setAvailableCopy(book.getAvailableCopy() + addBookRequest.getQuantity());
            bookRepo.save(book);
            AddBookResponse addBookResponse = new AddBookResponse();
            addBookResponse.setMessage("Book added to existing book successfully");
            return addBookResponse;
        } else {
            Book newBook = new Book();
            newBook.setAuthor(addBookRequest.getAuthor());
            newBook.setTitle(addBookRequest.getTitle());
            newBook.setTotalCopy(addBookRequest.getQuantity());
            newBook.setAvailableCopy(addBookRequest.getQuantity());
            bookRepo.save(newBook);
        }
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setMessage("Book added successfully");
        return addBookResponse;
    }

    @Override
    public String borrowBook(BorrowBookRequest borrowBookRequest) {

        Users user = usersRepo.findByUserId(borrowBookRequest.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepo.findByBookId(borrowBookRequest.getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopy() <= 0) {
            return "No available copies.";
        }

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        book.setAvailableCopy(book.getAvailableCopy() - 1);

        borrowRepo.save(record);
        bookRepo.save(book);

        return "Book borrowed successfully.";
    }

    @Override
    public String returnBook(UUID recordId) {
        BorrowRecord record = borrowRepo.findBorrowRecordById(recordId)
                .orElseThrow(()-> new RuntimeException("Record not found"));
        if (record.isReturned()) {
            return "Book Already returned";
        }
        record.setReturned(true);
        record.setReturnDate(LocalDate.now());

        Book book = record.getBook();
        book.setAvailableCopy(book.getAvailableCopy() + 1);
        borrowRepo.save(record);
        bookRepo.save(book);

        return " Book returned successfully";
    }

    @Override
    public String deleteBook(UUID BookId) {
        bookRepo.deleteById(BookId);
        return " Book deleted successfully";
    }

    @Override
    public String UpdateBook(UpdateBookRequest updateBookRequest) {
        Book book = bookRepo.findByBookId(updateBookRequest.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAuthor(updateBookRequest.getAuthor());
        book.setTitle(updateBookRequest.getTitle());
        return "Successfully updated the book";
    }


}




