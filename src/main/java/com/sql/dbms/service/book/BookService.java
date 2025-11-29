package com.sql.dbms.service.book;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.hibernate.boot.jaxb.mapping.internal.NotFoundActionMarshalling;
import org.springframework.stereotype.Service;

import com.sql.dbms.pojo.book.Book;
import com.sql.dbms.pojo.book.BookDto;
import com.sql.dbms.pojo.book.BookStatus;
import com.sql.dbms.pojo.book.Loan;
import com.sql.dbms.pojo.book.LoanDto;
import com.sql.dbms.repositry.book.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LoanService loanService;
    private final MemberService memberService;

    public Book addBook(String title , String author) {
        Book book = Book.builder()
                        .author(author)
                        .title(title)
                        .bookStatus(BookStatus.NOT_ISSUED)
                        .createdAt(Instant.now())
                        .build();
        return bookRepository.save(book);
                        
    }

    public List<BookDto> getAvaibleBook() {
        return bookRepository.findByBookStatus(BookStatus.NOT_ISSUED)
                .stream()
                .map(x -> {
                    return new BookDto(x.getTitle(), x.getAuthor(), x.getId());
                })
                .toList();
    }

    public LoanDto borrowBook(Integer bookId , Integer memeberId) {
        Book book =bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        boolean isValidMember = memberService.isValidMember(memeberId);
        if(book.getBookStatus() == BookStatus.ISSUED || !isValidMember) {
            throw new RuntimeException("Book is Alreasy Ussudes");
        }
       

        return loanService.saveAndReturn(bookId, memeberId);
        
    }

    public void returnBook(LoanDto loanDto) {
        Book book =bookRepository.findById(loanDto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookStatus(BookStatus.NOT_ISSUED);
        loanService.markReturn(loanDto.getId());

    }

}
