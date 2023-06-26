package ru.mtsbank.port.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.dao.JettyDao;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.repository.JettyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JettyService {
    @Autowired
    private JettyRepository jettyRepository;
    private final ArrayList<JettyDao> jettyDaoList = new ArrayList<>();

    private void initList() {
        List<Jetty> jettyList = jettyRepository.findAll();
        for (Jetty jetty : jettyList) {
            JettyDao jettyDao = new JettyDao();
            jettyDao.setName(jetty.getName());
            jettyDao.setMaxShips(jetty.getShipsNum());
            jettyDao.setMaxCapacity(jetty.getCapacity());
            jettyDaoList.add(jettyDao);
        }
    }

    public List<Jetty> readAll() {
        return jettyRepository.findAll();
    }

    public JettyDao getState(String name) {
        JettyDao jettyDaoState = new JettyDao();
        if (jettyDaoList.isEmpty()) { initList(); }
        for (JettyDao jettyDao : jettyDaoList) {
            if (jettyDao.getName().equals(name)) {
                jettyDaoState = jettyDao;
            }
        }
        return jettyDaoState;
    }

    public Boolean addShip(String shipName, Integer capacity) {

        boolean unloaded = false;
        boolean locked = false;
        int lockedJettyCount = 0;

        if (jettyDaoList.isEmpty()) { initList(); }

        for (JettyDao jettyDao : jettyDaoList) {
            if (jettyDao.getShipsCount() < jettyDao.getMaxShips() &&
                    jettyDao.getCapacity() < jettyDao.getMaxCapacity() &&
                    !unloaded) {
                int index = jettyDaoList.indexOf(jettyDao);
                jettyDao.add(shipName, capacity);
                jettyDaoList.set(index, jettyDao);
                System.out.println("Корабль " +  shipName + " разгружается на причале " + jettyDao.getName());
                unloaded = true;
                System.out.println(jettyDao);
            } else {
                if (!unloaded) {
                    lockedJettyCount++;
                    System.out.println("Причал " + jettyDao.getName() + " занят");
                    if (lockedJettyCount == jettyDaoList.size()) {
                        locked = true;
                        System.out.println("Все причалы заняты");
                    }
                }
            }
        }
        return locked;
    }

    public Boolean removeShip(String shipName, Integer capacity) {
        boolean success = false;
        for (JettyDao jettyDao : jettyDaoList) {
            if (jettyDao.getShips().contains(shipName)) {
                jettyDao.remove(shipName, capacity);
                System.out.println("Корабль " + shipName + " покинул причал " + jettyDao.getName());
                success = true;
            }
        }
        return success;
    }

}
