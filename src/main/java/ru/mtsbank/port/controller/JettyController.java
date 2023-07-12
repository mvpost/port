package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mtsbank.port.dto.JettyDto;
import ru.mtsbank.port.mapper.JettyMapper;
import ru.mtsbank.port.service.JettyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JettyController {
    final JettyService jettyService;
    final JettyMapper jettyMapper;

    @GetMapping(value = "/jetties")
    private List<JettyDto> read() {
        return jettyMapper.map(jettyService.readAll());
    }

    @RequestMapping(value = "/jetties", method = RequestMethod.POST)
    private HttpStatus addShip(@RequestPart("file") MultipartFile file,
                              @RequestPart("name") String shipName,
                              @RequestPart("capacity") String capacity) {
        try {
            jettyService.addShip(shipName, Integer.parseInt(capacity), file);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}