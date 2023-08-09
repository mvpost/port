package ru.mtsbank.port.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Fish;
import ru.mtsbank.port.repository.FishRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService {

    private final FishRepository fishRepository;

    public List<Fish> readAll() {
        return fishRepository.findAll();
    }

    public Float calcCost(String fishName, Float fishCount) {
        return fishCount * fishRepository.findDistinctFirstByName(fishName).getPrice();
    }
}
