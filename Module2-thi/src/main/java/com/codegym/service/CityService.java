package com.codegym.service;

import com.codegym.model.City;
import com.codegym.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    Iterable<City> findAll();
    City findById(Long id);
    void save(City city);
    void remove(Long id);
    Page<City> findByCountry(Country country, Pageable pageable);
    Page<City> findAll(Pageable pageable);
}
