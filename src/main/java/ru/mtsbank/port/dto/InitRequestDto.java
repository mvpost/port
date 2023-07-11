package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitRequestDto {
    public String name;
    public String type;
}