package com.sql.dbms.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {

    private Integer id;

    // Foreign key to the Member entity
    private Integer memberId;

    // Foreign key to the Book entity
    private Integer bookId;
}
