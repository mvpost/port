package ru.mtsbank.port.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.CountryDto;
import ru.mtsbank.port.exception.CountryNotFoundException;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.mapper.CountryMapper;
import ru.mtsbank.port.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
@Validated
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping()
    List<CountryDto> read() {
        return countryMapper.map(countryService.readAll());
    }

    @GetMapping("/{id}")
    List<CountryDto> read(@PathVariable("id") @NotNull Integer id) {
        return countryMapper.map(countryService.read(id));
    }

    @GetMapping("/random/{name}")
    CountryDto read(@PathVariable("name") @NotBlank String name) {
        return countryMapper.map(countryService.getRandomCountry(name));
    }

    @PostMapping()
    CountryDto create(@RequestBody @Valid Country country) {
        return countryMapper.map(countryService.create(country));
    }

    @PutMapping("/{id}")
    CountryDto update(@RequestBody @Valid Country country, @PathVariable @NotNull Integer id)
            throws CountryNotFoundException {
        return countryMapper.map(countryService.update(country, id));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") @NotNull Integer id) throws CountryNotFoundException {
        countryService.delete(id);
    }

}