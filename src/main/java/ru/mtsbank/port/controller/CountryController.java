package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @PutMapping("/countries/{id}")
    Country update(@RequestBody Country country, @PathVariable int id) {
        return countryService.update(country, id);
    }

    @DeleteMapping(value = "/countries/{id}")
    void delete(@PathVariable(name = "id") int id) {
        countryService.delete(id);
    }

    @GetMapping(value = "/countries/{id}")
    Country read(@PathVariable(name = "id") int id) {
        return countryService.read(id);
    }

    @GetMapping("/countries/random/{name}")
    ResponseEntity<CountryDto> read(@PathVariable(name = "name") String name) {
        final Country country = countryService.getRandomCountry(name);

        return country != null
                ? new ResponseEntity<>(countryMapper.map(country), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}