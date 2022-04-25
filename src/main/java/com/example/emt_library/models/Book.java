package com.example.emt_library.models;

import com.example.emt_library.models.enums.Category;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated (value = EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private int availableCopies;


    public Book() {
    }

    public Book(String name, Category category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book(String name, Category category, Long author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author.setId(author);
        this.availableCopies = availableCopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
