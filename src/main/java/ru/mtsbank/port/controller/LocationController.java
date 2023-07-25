package ru.mtsbank.port.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.LocationDto;
import ru.mtsbank.port.dto.LocationRequestDto;

@RestController
public class LocationController {

    private String setLocation(String shipType) {
        return switch (shipType) {
            case "yacht" -> "/countries/random/";
            case "boat" -> "/fishes/cost";
            case "ship" -> "/jetties";
            default -> "";
        };
    }

    @PostMapping("/location")
    private ResponseEntity<LocationDto> get(@RequestBody @Valid LocationRequestDto locationRequestDto) {
        LocationDto locationDto = new LocationDto();
        locationDto.setName(locationRequestDto.name);
        locationDto.setLocation(setLocation(locationRequestDto.type));
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }
}