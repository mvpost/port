package ru.mtsbank.port.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mtsbank.port.model.JettyModel;
import ru.mtsbank.port.service.JettyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JettyController {
    private final JettyService jettyService;

    @GetMapping(value = "/jetties")
    public ResponseEntity<List<JettyModel>> read() {
        final List<JettyModel> jettyModelList = jettyService.readAll();
        return jettyModelList != null
                ? new ResponseEntity<>(jettyModelList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/jetties", method = RequestMethod.POST)
    public HttpStatus addShip(@RequestPart("file") MultipartFile file,
                              @RequestPart("name") @NotNull String shipName,
                              @RequestPart("capacity") @NotNull String capacity) {
        try {
            jettyService.addShip(shipName, Integer.parseInt(capacity), file);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.LOCKED;
        }
    }
}