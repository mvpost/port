package ru.mtsbank.port.repository;

public interface JettyExtRepository {
    Integer getCurrentShipNumber(String name);
    void setCurrentShipNumber(String name);
    Integer getCurrentCapacity(String name);
    void setCurrentCapacity(String name, Integer value);
}
