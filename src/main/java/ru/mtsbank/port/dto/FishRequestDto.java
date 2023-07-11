package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FishRequestDto {
    public String name;
    public Float count;
    public Float cost;
}
