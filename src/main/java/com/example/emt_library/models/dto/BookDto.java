package com.example.emt_library.models.dto;

import com.example.emt_library.models.Author;
import com.example.emt_library.models.enums.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long author;

    private int availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
