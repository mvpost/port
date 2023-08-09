package ru.mtsbank.port.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.FishDto;
import ru.mtsbank.port.mapper.FishMapper;
import ru.mtsbank.port.service.FishService;

import java.util.List;

@RestController
@RequestMapping("/fishes")
@RequiredArgsConstructor
@Validated
public class FishController {
    private final FishService fishService;
    private final FishMapper fishMapper;

    @GetMapping()
    List<FishDto> read() {
        return fishMapper.map(fishService.readAll());
    }

    @GetMapping ("/cost")
    Float cost(@RequestParam("fishName") @NotBlank String fishName,
               @RequestParam("fishCount") @NotNull @Positive String fishCount) {
        return fishService.calcCost(fishName, Float.parseFloat(fishCount));
    }

}