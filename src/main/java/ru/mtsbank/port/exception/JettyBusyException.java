package ru.mtsbank.port.exception;

public class JettyBusyException extends RuntimeException {
    public JettyBusyException() {
        super("Все причалы заняты");
    }
}
