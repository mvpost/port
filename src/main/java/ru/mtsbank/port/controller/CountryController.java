package ru.mtsbank.port.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.CountryDto;
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
    List<Country> read() {
        return countryService.readAll();
    }

    @PostMapping("/countries")
    Country create(@RequestBody @Valid Country country) {
        return countryService.create(country);
    }

    @PutMapping("/countries/{id}")
    Country update(@RequestBody @Valid Country country, @PathVariable int id) {
        return countryService.update(country, id);
    }

    @DeleteMapping("/countries/{id}")
    void delete(@PathVariable(name = "id") int id) {
        countryService.delete(id);
    }

    @GetMapping("/countries/{id}")
    List<Country> read(@PathVariable(name = "id") int id) {
        return countryService.read(id);
    }

    @GetMapping("/countries/random/{name}")
    CountryDto read(@PathVariable(name = "name") String name) {
        return countryMapper.map(countryService.getRandomCountry(name));
    }

}