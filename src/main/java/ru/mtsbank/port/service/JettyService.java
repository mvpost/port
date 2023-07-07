package ru.mtsbank.port.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.bo.JettyBO;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.repository.JettyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class JettyService {
    @Autowired
    private JettyRepository jettyRepository;
    private final List<JettyBO> jettyBOList = Collections.synchronizedList(new ArrayList<>());
    private final ReentrantLock reLock = new ReentrantLock(true);

    private void initList() {
        List<Jetty> jettyList = jettyRepository.findAll();
        reLock.lock();
        try {
            for (Jetty jetty : jettyList) {
                JettyBO jettyBO = new JettyBO();
                jettyBO.setName(jetty.getName());
                jettyBO.setMaxShips(jetty.getShipsNum());
                jettyBO.setMaxCapacity(jetty.getCapacity());
                jettyBOList.add(jettyBO);
            }
        } finally {
            reLock.unlock();
        }
    }

    public List<Jetty> readAll() {
        return jettyRepository.findAll();
    }

    public JettyBO getState(String name) {
        JettyBO jettyBOState = new JettyBO();
        if (jettyBOList.isEmpty()) { initList(); }
        for (JettyBO jettyBO : jettyBOList) {
            if (jettyBO.getName().equals(name)) {
                jettyBOState = jettyBO;
            }
        }
        return jettyBOState;
    }

    public Boolean addShip(String shipName, Integer capacity) {
        reLock.lock();
        try {
            boolean unloaded = false;
            boolean locked = false;
            int lockedJettyCount = 0;

            if (jettyBOList.isEmpty()) { initList(); }

            for (JettyBO jettyBO : jettyBOList) {
                if (jettyBO.getShipsCount() < jettyBO.getMaxShips() &&
                        jettyBO.getCapacity() < jettyBO.getMaxCapacity() &&
                        !unloaded) {
                    int index = jettyBOList.indexOf(jettyBO);
                    jettyBO.add(shipName, capacity);
                    jettyBOList.set(index, jettyBO);
                    System.out.println("Корабль " +  shipName + " разгружается на причале " + jettyBO.getName());
                    unloaded = true;
                } else {
                    if (!unloaded) {
                        lockedJettyCount++;
                        System.out.println("Причал " + jettyBO.getName() + " занят");
                        if (lockedJettyCount == jettyBOList.size()) {
                            locked = true;
                            System.out.println("Все причалы заняты");
                        }
                    }
                }
            }
            return locked;
        } finally {
            reLock.unlock();
        }
    }

    public Boolean removeShip(String shipName, Integer capacity) {
        boolean success = false;
        for (JettyBO jettyBO : jettyBOList) {
            if (jettyBO.getShips().contains(shipName)) {
                jettyBO.remove(shipName, capacity);
                System.out.println("Корабль " + shipName + " покинул причал " + jettyBO.getName());
                success = true;
            }
        }
        return success;
    }

}
