package ru.mtsbank.port.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(int id) {
        super("Не найдена страна " + id);
    }
}
