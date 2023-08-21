package ru.mtsbank.port.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class JettyBusyAdvice {
    @ResponseBody
    @ExceptionHandler(JettyBusyException.class)
    @ResponseStatus(HttpStatus.LOCKED)
    String jettyBusyHandler(JettyBusyException ex) {
        return ex.getMessage();
    }
}
