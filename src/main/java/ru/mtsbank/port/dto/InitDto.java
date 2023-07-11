package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitDto {
    private String name;
    private String location;
    private String guid;
}