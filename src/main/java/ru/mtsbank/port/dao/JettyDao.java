package ru.mtsbank.port.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JettyDao {
    private String name;
    private Integer ships = 0;
    private Integer capacity = 0;
    private Short maxShips = 0;
    private Integer maxCapacity = 0;

    @Override
    public String toString() {
        return "JettyDao{" +
                "name='" + name + '\'' +
                ", ships=" + ships +
                ", capacity=" + capacity +
                ", maxShips=" + maxShips +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
