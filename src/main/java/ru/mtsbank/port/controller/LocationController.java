package ru.mtsbank.port.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.exception.LocationNotFoundException;
import ru.mtsbank.port.service.JettyService;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@Validated
public class LocationController {
    private final JettyService jettyService;

    @GetMapping("/{type}")
    public String setLocation(@PathVariable @NotBlank String type,
                           @Nullable @Positive @RequestParam Integer capacity) {
        return switch (type) {
            case "yacht" -> "/countries/random/";
            case "boat" -> "/fishes/cost";
            case "ship" -> String.format("/jetties/%s", jettyService.getFreeJettyName(capacity));
            default -> throw new LocationNotFoundException(type);
        };
    }
}