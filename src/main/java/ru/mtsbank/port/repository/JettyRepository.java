package ru.mtsbank.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtsbank.port.entity.Jetty;

public interface JettyRepository extends JpaRepository<Jetty, Integer> {
}
