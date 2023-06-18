package ru.mtsbank.port.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.model.Country;
import ru.mtsbank.port.service.CountryService;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(value = "/country")
    public ResponseEntity<?> create(@RequestBody Country country) {
        countryService.create(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/country")
    public ResponseEntity<List<Country>> read() {
        final List<Country> country = countryService.readAll();
        return country != null &&  !country.isEmpty()
                ? new ResponseEntity<>(country, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/country/getnew/{name}")
    public ResponseEntity<List<Country>> read(@PathVariable(name = "name") String name) {
        final List<Country> country = countryService.getNewCountry(name);

        return country != null &&  !country.isEmpty()
                ? new ResponseEntity<>(country, HttpStatus.OK)
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
