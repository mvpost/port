package ru.mtsbank.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtsbank.port.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findDistinctFirstByNameNotLike(String name);
}
