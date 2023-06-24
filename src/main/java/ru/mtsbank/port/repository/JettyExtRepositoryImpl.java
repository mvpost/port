package ru.mtsbank.port.repository;

public class JettyExtRepositoryImpl implements JettyExtRepository {
    private Integer currentShipNumber = 0;
    private Integer currentCapacity = 0;
    @Override
    public Integer getCurrentShipNumber(String name) {
        return currentShipNumber;
    }
    @Override
    public void setCurrentShipNumber(String name) {
        this.currentShipNumber++;
    }
    @Override
    public Integer getCurrentCapacity(String name) {
        return currentCapacity;
    }
    @Override
    public void setCurrentCapacity(String name, Integer value) {
        currentCapacity += value;
    }
}
