package ru.mtsbank.port.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String location) {
        super("Не найден тип судна: " + location);
    }
}