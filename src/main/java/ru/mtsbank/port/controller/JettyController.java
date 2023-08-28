package ru.mtsbank.port.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mtsbank.port.dto.JettyDto;
import ru.mtsbank.port.mapper.JettyMapper;
import ru.mtsbank.port.service.JettyService;

import java.util.List;

@RestController
@RequestMapping("/jetties")
@RequiredArgsConstructor
public class JettyController {
    private final JettyService jettyService;
    private final JettyMapper jettyMapper;

    @GetMapping()
    private List<JettyDto> read() {
        return jettyMapper.map(jettyService.readAll());
    }

    @PostMapping()
    private HttpStatus addShip(@RequestPart("name") @NotBlank String shipName,
                               @RequestPart("capacity") @Positive String capacity,
                               @RequestPart("jettyName") @Nullable String jettyName,
                               @RequestPart("file") MultipartFile file) {
        try {
            jettyService.requestJetty(shipName, Integer.parseInt(capacity), jettyName, file);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}