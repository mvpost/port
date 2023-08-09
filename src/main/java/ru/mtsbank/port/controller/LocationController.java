package ru.mtsbank.port.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.exception.LocationNotFoundException;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@Validated
public class LocationController {

    @GetMapping("/{type}")
    public String setLocation(@PathVariable @NotBlank String type,
                           @Nullable @Positive @RequestParam Integer volume) {
        return switch (type) {
            case "yacht" -> "/countries/random/";
            case "boat" -> "/fishes/cost";
            case "ship" -> "/jetties";
            case "transport" -> String.format("/docks/%s", volume);
            default -> throw new LocationNotFoundException(type);
        };
    }
}