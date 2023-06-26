package ru.mtsbank.port.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@RequiredArgsConstructor
public class JettyDao {
    private String name;
    private Short maxShips = 0;
    private Integer maxCapacity = 0;
    private AtomicInteger shipsCount = new AtomicInteger(0);
    private AtomicInteger capacity = new AtomicInteger(0);
    @Getter
    private List<String> ships = Collections.synchronizedList(new ArrayList<>());

    public Integer getShipsCount() {
        return shipsCount.intValue();
    }

    public Integer getCapacity() {
        return capacity.intValue();
    }

    public void add(String shipName, Integer capacity) {
        this.shipsCount.getAndIncrement();
        this.capacity.addAndGet(capacity);
        ships.add(shipName);
    }

    public void remove(String shipName, Integer capacity) {
        this.shipsCount.getAndDecrement();
        this.capacity.addAndGet(-capacity);
        ships.remove(shipName);
    }

    @Override
    public String toString() {
        return "JettyDao{" +
                "name='" + name + '\'' +
                ", ships=" + shipsCount +
                ", capacity=" + capacity +
                ", maxShips=" + maxShips +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
