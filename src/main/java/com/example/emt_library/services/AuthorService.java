package com.example.emt_library.services;

import com.example.emt_library.models.Author;
import com.example.emt_library.models.Country;

import java.util.List;

public interface AuthorService {
    Author create(String name, String surname, Long countryId);
    List<Author> listAll();
}
