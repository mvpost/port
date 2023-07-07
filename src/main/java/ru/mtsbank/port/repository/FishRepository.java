package ru.mtsbank.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtsbank.port.entity.Fish;

public interface FishRepository extends JpaRepository<Fish, Integer> {
    Fish findDistinctFirstByName(String name);
}
