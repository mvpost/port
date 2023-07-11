package ru.mtsbank.port.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Fish;
import ru.mtsbank.port.repository.FishRepository;
import java.util.List;

@Service
public class FishService {
    @Autowired
    FishRepository fishRepository;

    public List<Fish> readAll() {
        return fishRepository.findAll();
    }

    public Float calcCost(@NotNull String fishName, @NotNull Float fishCount) {
        return fishCount * fishRepository.findDistinctFirstByName(fishName).getPrice();
    }
}
