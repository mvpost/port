package ru.mtsbank.port.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.exception.CountryNotFoundException;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.repository.CountryRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Country create(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    public List<Country> read(int id) {
            return countryRepository.findAllById(Collections.singleton(id));
    }

    public Country update(Country newCountry, int id) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(newCountry.getName());
                    country.setLat(newCountry.getLat());
                    country.setLon(newCountry.getLon());
                    country.setUpdatedAt(LocalDateTime.now());
                    return countryRepository.save(country);
                })
                .orElseThrow(() -> new CountryNotFoundException(id));
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