package com.codegym.service;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        cityRepository.delete(id);
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }
    @Override
    public Page<City> findByCountry(Country country, Pageable pageable) {
        return cityRepository.findByCountry(country,pageable);
    }
}
