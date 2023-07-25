package ru.mtsbank.port.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.CountryDto;
import ru.mtsbank.port.dto.CountryNotFoundException;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.mapper.CountryMapper;
import ru.mtsbank.port.service.CountryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping("/countries")
    List<CountryDto> read() {
        return countryMapper.map(countryService.readAll());
    }

    @GetMapping("/countries/{id}")
    List<CountryDto> read(@PathVariable("id") int id) {
        return countryMapper.map(countryService.read(id));
    }

    @GetMapping("/countries/random/{name}")
    CountryDto read(@PathVariable("name") String name) {
        return countryMapper.map(countryService.getRandomCountry(name));
    }

    @PostMapping("/countries")
    CountryDto create(@RequestBody @Valid Country country) {
        return countryMapper.map(countryService.create(country));
    }

    @PutMapping("/countries/{id}")
    CountryDto update(@RequestBody @Valid Country country, @PathVariable int id)
            throws CountryNotFoundException {
        return countryMapper.map(countryService.update(country, id));
    }

    @DeleteMapping("/countries/{id}")
    void delete(@PathVariable("id") int id)
            throws CountryNotFoundException {
        countryService.delete(id);
    }

}