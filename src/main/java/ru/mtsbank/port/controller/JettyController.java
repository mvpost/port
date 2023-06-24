package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.request.ShipRequest;
import ru.mtsbank.port.service.JettyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JettyController {
    private final JettyService jettyService;

    @GetMapping(value = "/jetty")
    public ResponseEntity<List<Jetty>> read() {
        final List<Jetty> jettyList = jettyService.readAll();
        return jettyList != null
                ? new ResponseEntity<>(jettyList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/jetty")
    public ResponseEntity<Jetty> get(@RequestBody ShipRequest shipRequest) {
        //Проба -->
        final Jetty jetty = jettyService.getByName("First");
        jettyService.addShip(shipRequest.name, shipRequest.capacity);
        final boolean allowed = true;
        return allowed
                ? new ResponseEntity<>(jetty, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.LOCKED);
    }
}