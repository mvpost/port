package ru.mtsbank.port.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JettyDto {
    private String name;
    private Short maxShips = 0;
    private Integer maxCapacity = 0;
    private Integer shipsCount = 0;
    private Integer capacity = 0;
}

