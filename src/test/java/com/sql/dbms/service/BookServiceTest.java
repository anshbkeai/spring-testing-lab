package com.sql.dbms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import com.sql.dbms.pojo.book.Book;
import com.sql.dbms.pojo.book.BookStatus;
import com.sql.dbms.pojo.book.Loan;
import com.sql.dbms.pojo.book.LoanDto;
import com.sql.dbms.pojo.book.Member;
import com.sql.dbms.repositry.book.BookRepository;
import com.sql.dbms.service.book.BookService;
import com.sql.dbms.service.book.LoanService;
import com.sql.dbms.service.book.MemberService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private  BookRepository bookRepository;
    @Mock
    private  LoanService loanService;
    @Mock
    private  MemberService memberService;

    @InjectMocks
    private BookService bookService;

    private Book testBook;
    private Member testMember;
    private Loan testLoan;
    private LoanDto testLoanDto;

    @BeforeEach
    void setup() {
        testBook = Book.builder()
                        .id(1)
                        .title("Test Book")
                        .author("Test Author")
                        .bookStatus(BookStatus.NOT_ISSUED)
                        .build();
        testMember = Member.builder()
                            .id(1)
                            .name("Test Member")
                            .email("testmember@example.com")
                            .build();
        testLoan = Loan.builder()
                        .id(1)
                        .bookId(testBook.getId())
                        .memberId(testMember.getId())
                        .build();
        testLoanDto = LoanDto.builder()
                            .id(1)
                            .bookId(testBook.getId())
                            .memberId(testMember.getId())
                            .build();
    }

    // we are testing the methods here in the book service class
    // so testing fundamentsal about the unit testing 

    @Test
    void testAddBook() {
        // whne is done 
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        // Call the Methids 
        Book book = bookService.addBook("Test Book", "Test Author");

        assertNotNull(book);
        assertEquals(testBook, book);

        verify(bookRepository).save(any());

    }

    @Test
    void testBorrowBookIfPresentAndValidMember() {
        // whne the Book is Present and Momber is Vlaud
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(testBook));
        when(memberService.isValidMember(anyInt())).thenReturn(true);

        when(loanService.saveAndReturn(anyInt(), anyInt())).thenReturn(testLoanDto);

        LoanDto loanDto = bookService.borrowBook(testBook.getId(), testMember.getId());

        assertNotNull(loanDto);
        assertEquals(testLoanDto, loanDto);

        verify(loanService,times(1)).saveAndReturn(anyInt(), anyInt());
    }

    @Test
    void testBorrowBookIfNotPresentAndValidMember() {
        // whne the Book is Present and Momber is Vlaud
        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
      //  when(memberService.isValidMember(anyInt())).thenReturn(true);

       // when(loanService.saveAndReturn(anyInt(), anyInt())).thenReturn(testLoanDto);

        //LoanDto loanDto = bookService.borrowBook(testBook.getId(), testMember.getId());

        final RuntimeException exception = assertThrows(
                                        RuntimeException.class, 
                                    () -> bookService.borrowBook(testBook.getId(), testMember.getId())
                                    );

       
        assertEquals("Book not found" , exception.getMessage());

        verify(loanService,times(0)).saveAndReturn(anyInt(), anyInt());
    }

    

}