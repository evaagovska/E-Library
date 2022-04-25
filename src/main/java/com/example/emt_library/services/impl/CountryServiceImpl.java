package com.example.emt_library.services.impl;

import com.example.emt_library.models.Country;
import com.example.emt_library.repositories.BooksRepository;
import com.example.emt_library.repositories.CountryRepository;
import com.example.emt_library.services.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country create(String name, String continent) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = new Country(name,continent);
        countryRepository.save(c);
        return c;

    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }
}
