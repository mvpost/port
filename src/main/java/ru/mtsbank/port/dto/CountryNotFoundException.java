package ru.mtsbank.port.dto;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(int id) {
        super("Не найдена страна " + id);
    }
}
