package ru.mtsbank.port.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Data
@Component
public class JettyModel {
    private String name;
    private Short maxShipsCount = 0;
    private Integer maxCapacity = 0;
    private Integer curShipsCount = 0;
    private Integer curCapacity = 0;
}
