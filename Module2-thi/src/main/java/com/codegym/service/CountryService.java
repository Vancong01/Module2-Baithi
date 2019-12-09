package com.codegym.service;

import com.codegym.model.Country;

import java.util.Locale;

public interface CountryService {
    Iterable<Country> findAll();
    Country findById(Long id);
    void save (Country country);
    void remove(Long id);

}
