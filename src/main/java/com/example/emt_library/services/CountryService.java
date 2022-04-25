package com.example.emt_library.services;

import com.example.emt_library.models.Country;

import java.util.List;

public interface CountryService {
    Country create(String name, String continent);
    List<Country> listAll();
}
