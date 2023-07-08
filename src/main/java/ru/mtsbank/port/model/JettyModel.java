package ru.mtsbank.port.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JettyModel {
    private String name;
    private Short maxShips = 0;
    private Integer maxCapacity = 0;
    private Integer shipsCount = 0;
    private Integer capacity = 0;

    @Override
    public String toString() {
        return "JettyModel{" +
                "name='" + name + '\'' +
                ", ships=" + shipsCount +
                ", capacity=" + capacity +
                ", maxShips=" + maxShips +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
