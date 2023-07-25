package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRequestDto {
    public String name;
    public String type;
}