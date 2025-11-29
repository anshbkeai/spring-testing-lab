package com.sql.dbms.pojo.book;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "loan")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    // --- Fields based on your schema ---

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way the ID is generated (e.g., auto-increment)
    private Integer id;

    // Foreign key to the Member entity
    private Integer memberId;

    // Foreign key to the Book entity
    private Integer bookId;

    // Timestamp when the book was issued
    private Instant issuedAt;

    // Timestamp when the book was returned (can be null if not yet returned)
    private Instant returnedAt;
}