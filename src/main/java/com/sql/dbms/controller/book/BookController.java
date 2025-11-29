package com.sql.dbms.controller.book;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sql.dbms.pojo.book.Book;
import com.sql.dbms.pojo.book.BookDto;
import com.sql.dbms.pojo.book.LoanDto;
import com.sql.dbms.service.book.BookService;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books") // Base path for all book endpoints
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // --- Inner DTOs for Request Payloads ---

    // DTO for adding a new book (Request Body)
    @Data
    @Builder
    private static class AddBookRequest {
        private String title;
        private String author;
    }

    // DTO for borrowing a book (Request Body)
    @Data
    @Builder
    private static class BorrowBookRequest {
        private Integer bookId;
        private Integer memberId;
    }


    // --- Controller Endpoints ---

    /**
     * Endpoint to add a new book to the library.
     * Maps to: POST /api/v1/books
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestParam String title ,@RequestParam String author) {
        Book newBook = bookService.addBook(title, author);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a list of all currently available books.
     * Maps to: GET /api/v1/books/available
     */
    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAvailableBooks() {
        List<BookDto> availableBooks = bookService.getAvaibleBook();
        return ResponseEntity.ok(availableBooks);
    }

    /**
     * Endpoint to handle a member borrowing a specific book.
     * Maps to: POST /api/v1/books/borrow
     */
    @PostMapping("/borrow")
    public ResponseEntity<LoanDto> borrowBook(@RequestParam Integer bookId ,@RequestParam Integer memberId  ) {
        // Note: The service uses 'memeberId', I've assumed a typo and corrected to 'memberId' in the DTO
        LoanDto loanDetails = bookService.borrowBook(bookId, memberId);
        return new ResponseEntity<>(loanDetails, HttpStatus.OK);
    }

    /**
     * Endpoint to handle a book return.
     * Maps to: PUT /api/v1/books/return
     *
     * @param loanDto Requires the ID of the loan that is being returned.
     */
    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestBody LoanDto loanDto) {
        bookService.returnBook(loanDto);
        // Returns 204 No Content to indicate successful processing without a body
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

}