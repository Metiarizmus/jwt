package com.lessons.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    @Id
    private String id;
    private String author;
    private String nameBook;
    private String description;


    public Book(String author, String nameBook, String description) {
        this.author = author;
        this.nameBook = nameBook;
        this.description = description;
    }
}
