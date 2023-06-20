package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.entity.Fish;
import ru.mtsbank.port.request.FishRequest;
import ru.mtsbank.port.service.FishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    @GetMapping(value = "/fish")
    public ResponseEntity<List<Fish>> read() {
        final List<Fish> fish = fishService.readAll();
        return fish != null
                ? new ResponseEntity<>(fish, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/fish/cost")
    public ResponseEntity<Float> cost(@RequestBody FishRequest fishRequest) {
        final Float fishCost = fishService.calcCost(fishRequest.name, fishRequest.count);
        return fishCost != null
                ? new ResponseEntity<>(fishCost, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}