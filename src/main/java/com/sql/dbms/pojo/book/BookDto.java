package com.sql.dbms.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class BookDto {

    private String title;
    private String author;
    private Integer id;
}
