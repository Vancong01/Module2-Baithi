package com.codegym.service;

import com.codegym.model.Country;
import com.codegym.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryServiceImpl implements  CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void remove(Long id) {
        countryRepository.delete(id);
    }
}
