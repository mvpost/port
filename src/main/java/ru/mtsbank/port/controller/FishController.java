package ru.mtsbank.port.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.port.dto.FishDto;
import ru.mtsbank.port.entity.Fish;
import ru.mtsbank.port.mapper.FishMapper;
import ru.mtsbank.port.request.FishRequest;
import ru.mtsbank.port.service.FishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;
    private final FishMapper fishMapper;

    @GetMapping(value = "/fishes")
    public ResponseEntity<List<Fish>> read() {
        final List<Fish> fish = fishService.readAll();
        return fish != null
                ? new ResponseEntity<>(fish, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/fishes/cost")
    public ResponseEntity<FishDto> cost(@RequestBody FishRequest fishRequest) {
        final float fishCost = fishService.calcCost(fishRequest.name, fishRequest.count);
        FishDto fishDto = fishMapper.map(fishRequest);
        fishDto.setCost(fishCost);
        return fishCost > 0
                ? new ResponseEntity<>(fishDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}