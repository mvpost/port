package ru.mtsbank.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mtsbank.port.entity.Jetty;

public interface JettyRepository extends JpaRepository<Jetty, Integer> {
    @Query("select j from Jetty j where j.name = :name")
    Jetty findByName(@Param("name") String name);

}
