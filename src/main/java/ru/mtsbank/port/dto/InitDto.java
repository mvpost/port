package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InitDto {
    private String name;
    private String location;
    private String guid;
}