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

    @PostMapping(value = "/countries")
    public ResponseEntity<?> create(@RequestBody Country country) {
        countryService.create(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryDto>> read() {
        final List<Country> country = countryService.readAll();
        return country != null
                ? new ResponseEntity<>(countryMapper.map(country), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/countries/random/{name}")
    public ResponseEntity<CountryDto> read(@PathVariable(name = "name") String name) {
        final Country country = countryService.getRandomCountry(name);

        return country != null
                ? new ResponseEntity<>(countryMapper.map(country), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/countries/{id}")
    public ResponseEntity<Country> read(@PathVariable(name = "id") int id) {
        final Country country = countryService.read(id);

        return country != null
                ? new ResponseEntity<>(country, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/countries/{id}")
    public HttpStatus update(@PathVariable(name = "id") int id, @RequestBody Country country) {
        countryService.update(country, id);
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/countries/{id}")
    public HttpStatus delete(@PathVariable(name = "id") int id) {
        countryService.delete(id);
        return HttpStatus.OK;
    }
}