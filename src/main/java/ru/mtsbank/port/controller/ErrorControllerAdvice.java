package ru.mtsbank.port.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mtsbank.port.dto.ErrorDto;
import ru.mtsbank.port.exception.CountryNotFoundException;
import ru.mtsbank.port.exception.JettyBusyException;
import ru.mtsbank.port.exception.LocationNotFoundException;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(CountryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder().message(e.getLocalizedMessage())
                .build());
    }

    @ExceptionHandler(JettyBusyException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(JettyBusyException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder().message(e.getLocalizedMessage())
                .build());
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(LocationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder().message(e.getLocalizedMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDto.builder().message(e.getLocalizedMessage())
                .build());
    }
}
