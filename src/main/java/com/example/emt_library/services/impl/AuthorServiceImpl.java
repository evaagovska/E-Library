package com.example.emt_library.services.impl;

import com.example.emt_library.models.Author;
import com.example.emt_library.models.Country;
import com.example.emt_library.models.exceptions.CountryNotFoundException;
import com.example.emt_library.repositories.AuthorRepository;
import com.example.emt_library.repositories.CountryRepository;
import com.example.emt_library.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(CountryRepository countryRepository, AuthorRepository authorRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));

        Author author = new Author(name,surname, country);
        authorRepository.save(author);
        return author;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }
}
