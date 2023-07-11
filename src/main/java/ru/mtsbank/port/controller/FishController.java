package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.entity.Fish;
import ru.mtsbank.port.service.FishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    @GetMapping("/fishes")
    List<Fish> read() {
        return fishService.readAll();
    }

    @GetMapping ("/fishes/cost")
    Float cost(@RequestParam("fishName") String fishName, @RequestParam("fishCount") String fishCount) {
        return fishService.calcCost(fishName, Float.parseFloat(fishCount));
    }

}