package ru.mtsbank.port.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.InitDto;
import ru.mtsbank.port.request.InitRequest;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InitController {

    private String setLocation(@NotNull String shipType) {
        return switch (shipType) {
            case "yacht" -> "/country/random/";
            case "boat" -> "/fish/cost";
            case "ship" -> "/jetty";
            default -> "";
        };
    }

    @PostMapping(value = "/init")
    public ResponseEntity<InitDto> get(@RequestBody @NotNull InitRequest initRequest) {
        UUID uuid = UUID.randomUUID();
        InitDto initDto = new InitDto();
        initDto.setName(initRequest.name);
        initDto.setGuid(uuid.toString());
        initDto.setLocation(setLocation(initRequest.type));
        return new ResponseEntity<>(initDto, HttpStatus.OK);
    }
}