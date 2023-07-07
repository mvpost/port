package ru.mtsbank.port.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Getter
@Setter
@RequiredArgsConstructor
public class JettyDao {
    private String name;
    private volatile Short maxShips = 0;
    private volatile Integer maxCapacity = 0;
    private AtomicInteger shipsCount = new AtomicInteger(0);
    private AtomicInteger capacity = new AtomicInteger(0);
    @Getter
    private List<String> ships = Collections.synchronizedList(new ArrayList<>());
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public Integer getShipsCount() {
        readLock.lock();
        try {
            return shipsCount.intValue();
        } finally {
            readLock.unlock();
        }
    }

    public Integer getCapacity() {
        readLock.lock();
        try {
            return capacity.intValue();
        } finally {
            readLock.unlock();
        }
    }

    public void add(String shipName, Integer capacity) {
        writeLock.lock();
        try {
            this.shipsCount.getAndIncrement();
            this.capacity.addAndGet(capacity);
            ships.add(shipName);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String shipName, Integer capacity) {
        writeLock.lock();
        try {
            this.shipsCount.getAndDecrement();
            this.capacity.addAndGet(-capacity);
            ships.remove(shipName);
        } finally {
            writeLock.unlock();
        }
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
