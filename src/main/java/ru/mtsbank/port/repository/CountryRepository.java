package ru.mtsbank.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtsbank.port.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
