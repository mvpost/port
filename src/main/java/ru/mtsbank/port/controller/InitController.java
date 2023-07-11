package ru.mtsbank.port.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.InitDto;
import ru.mtsbank.port.dto.InitRequestDto;
import java.util.UUID;

@RestController
public class InitController {

    private String setLocation(@NotNull String shipType) {
        return switch (shipType) {
            case "yacht" -> "/countries/random/";
            case "boat" -> "/fishes/cost";
            case "ship" -> "/jetties";
            default -> "";
        };
    }

    @PostMapping(value = "/init")
    public ResponseEntity<InitDto> get(@RequestBody @NotNull InitRequestDto initRequestDto) {
        UUID uuid = UUID.randomUUID();
        InitDto initDto = new InitDto();
        initDto.setName(initRequestDto.name);
        initDto.setGuid(uuid.toString());
        initDto.setLocation(setLocation(initRequestDto.type));
        return new ResponseEntity<>(initDto, HttpStatus.OK);
    }
}