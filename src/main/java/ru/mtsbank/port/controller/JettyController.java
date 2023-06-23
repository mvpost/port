package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.component.JettyExt;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.request.ShipRequest;
import ru.mtsbank.port.service.JettyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JettyController {
    private final JettyService jettyService;
    private Integer currentShipNum = 0;
    private Integer currentCapacity = 0;
    private Boolean allowed = true;

    JettyExt jettyExt = new JettyExt();

    @GetMapping(value = "/jetty")
    public ResponseEntity<List<Jetty>> read() {
        final List<Jetty> jetty = jettyService.readAll();
        return jetty != null
                ? new ResponseEntity<>(jetty, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/jetty")
    public ResponseEntity<Jetty> get(@RequestBody ShipRequest shipRequest) {
        final Jetty jetty = jettyService.read(7);

        if (currentShipNum + 1 <= jetty.getShipsNum() &&
                currentCapacity + shipRequest.capacity <= jetty.getCapacity()) {
                currentShipNum++;
                currentCapacity += shipRequest.capacity;
                System.out.println(currentShipNum + " " + currentCapacity);
        } else {
            allowed = false;
        }

        return allowed
                ? new ResponseEntity<>(jetty, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.LOCKED);
    }
}