package com.sql.dbms.repositry.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sql.dbms.pojo.book.Book;
import com.sql.dbms.pojo.book.BookStatus;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByBookStatus(BookStatus bookStatus);
}
