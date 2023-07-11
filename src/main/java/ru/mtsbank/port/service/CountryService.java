package ru.mtsbank.port.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public void create(Country country) {
        countryRepository.save(country);
    }

    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    public Country read(int id) {
        return countryRepository.getReferenceById(id);
    }

    public void update(Country country, int id) {
        countryRepository.save(country);
    }

    public void delete(int id) {
        countryRepository.deleteById(id);
    }

    public Country getRandomCountry(String countryName) {
        return countryRepository.findDistinctFirstByNameNotLike(countryName);
    }
}