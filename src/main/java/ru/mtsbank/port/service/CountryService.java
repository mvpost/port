package ru.mtsbank.port.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.dto.CountryNotFoundException;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public Country create(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    public Country read(int id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    public Country update(Country newCountry, int id) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(newCountry.getName());
                    country.setLat(newCountry.getLat());
                    country.setLon(newCountry.getLon());
                    return countryRepository.save(country);
                })
                .orElseGet(() -> {
                    newCountry.setId(id);
                    return countryRepository.save(newCountry);
                });
    }

    public void delete(int id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        } else {
            throw new CountryNotFoundException(id);
        }
    }

    public Country getRandomCountry(String countryName) {
        return countryRepository.findDistinctFirstByNameNotLike(countryName);
    }
}