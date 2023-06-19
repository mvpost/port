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

    @PostMapping(value = "/country")
    public ResponseEntity<?> create(@RequestBody Country country) {
        countryService.create(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/country")
    public ResponseEntity<List<CountryDto>> read() {
        final List<Country> country = countryService.readAll();
        return country != null
                ? new ResponseEntity<>(countryMapper.map(country), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/country/random/{name}")
    public ResponseEntity<CountryDto> read(@PathVariable(name = "name") String name) {
        final Country country = countryService.getRandomCountry(name);

        return country != null
                ? new ResponseEntity<>(countryMapper.map(country), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<Country> read(@PathVariable(name = "id") int id) {
        final Country country = countryService.read(id);

        return country != null
                ? new ResponseEntity<>(country, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/country/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Country country) {
        final boolean updated = countryService.update(country, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/country/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = countryService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}