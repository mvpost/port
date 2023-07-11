package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FishDto {
    private String name;
    private Float count;
    private Float cost;
}