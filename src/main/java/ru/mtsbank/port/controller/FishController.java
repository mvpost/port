package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.FishDto;
import ru.mtsbank.port.mapper.FishMapper;
import ru.mtsbank.port.service.FishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;
    private final FishMapper fishMapper;

    @GetMapping("/fishes")
    List<FishDto> read() {
        return fishMapper.map(fishService.readAll());
    }

    @GetMapping ("/fishes/cost")
    Float cost(@RequestParam("fishName") String fishName, @RequestParam("fishCount") String fishCount) {
        return fishService.calcCost(fishName, Float.parseFloat(fishCount));
    }

}