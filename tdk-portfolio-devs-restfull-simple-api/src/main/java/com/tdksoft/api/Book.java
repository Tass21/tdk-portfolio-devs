package com.tdksoft.api;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private UUID bookId;

    private String title;

    private String author;

    private String ISBN;

    private Integer publicationYear;

    public Book(String title, String author, String ISBN, Integer publicationYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
    }
}
