package ru.mtsbank.port.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dao.JettyDao;
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

    @GetMapping(value = "/jetty/state/{name}")
    public ResponseEntity<JettyDao> read(@PathVariable(name = "name") String name) {
        final JettyDao jettyDao = jettyService.getState(name);
        return jettyDao != null
                ? new ResponseEntity<>(jettyDao, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/jetty")
    public ResponseEntity<Jetty> get(@RequestBody @NotNull ShipRequest shipRequest) {
        final boolean locked = jettyService.addShip(shipRequest.name, shipRequest.capacity);
        return !locked
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.LOCKED);
    }

    @DeleteMapping(value = "/jetty")
    public ResponseEntity<Jetty> delete(@RequestBody @NotNull ShipRequest shipRequest) {
        final boolean success = jettyService.removeShip(shipRequest.name, shipRequest.capacity);
        return success
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}