package com.example.emt_library.models.dto;

import com.example.emt_library.models.Country;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class AuthorDto {

    private String name;

    private String surname;

    private Long country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
