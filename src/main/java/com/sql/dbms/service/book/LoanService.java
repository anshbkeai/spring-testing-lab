package com.sql.dbms.service.book;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.sql.dbms.pojo.book.Loan;
import com.sql.dbms.pojo.book.LoanDto;
import com.sql.dbms.repositry.book.LoanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanDto saveAndReturn(Integer bookId , Integer memeberId) {
        Loan loan = Loan.builder().bookId(bookId).issuedAt(Instant.now()).memberId(memeberId).build();
       loanRepository.save(loan);

        return new LoanDto(loan.getId(), loan.getMemberId()  , bookId);
    }

    public void markReturn(Integer id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        loan.setReturnedAt(Instant.now());
        loanRepository.save(loan);
    }
}
