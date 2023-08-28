package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JettyDto {
    private String name;
    private Short maxShipsCount = 0;
    private Integer maxCapacity = 0;
    private Integer curShipsCount = 0;
    private Integer curCapacity = 0;
}

