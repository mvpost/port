package ru.mtsbank.port.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ArrayList<JettyDao> jettyDaoList = new ArrayList<>();

    public List<Jetty> readAll() {
        return jettyRepository.findAll();
    }

    /*public Jetty read(int id) {
        return jettyRepository.getReferenceById(id);
    }*/

    public Jetty getByName(String name) {
        return jettyRepository.findByName(name);
    }

    public void addShip(String shipName, Integer capacity) {
        if (jettyDaoList.isEmpty()) {
            List<Jetty> jettyList = jettyRepository.findAll();
            for (Jetty jetty : jettyList) {
                JettyDao jettyDao = new JettyDao();
                jettyDao.setName(jetty.getName());
                jettyDao.setShips(0);
                jettyDao.setCapacity(0);
                jettyDao.setMaxShips(jetty.getShipsNum());
                jettyDao.setMaxCapacity(jetty.getCapacity());
                jettyDaoList.add(jettyDao);
            }
        }

        boolean unloaded = false;
        for (JettyDao jettyDao : jettyDaoList) {
            if (jettyDao.getShips() < jettyDao.getMaxShips() &&
                    jettyDao.getCapacity() < jettyDao.getMaxCapacity() &&
                    !unloaded) {
                int index = jettyDaoList.indexOf(jettyDao);
                jettyDao.setShips(jettyDao.getShips() + 1);
                jettyDao.setCapacity(jettyDao.getCapacity() + capacity);
                jettyDaoList.set(index, jettyDao);
                System.out.println("Корабль " +  shipName + " разгружается на причале " + jettyDao.getName());
                unloaded = true;
                System.out.println(jettyDao.toString());
            } else {
                if (!unloaded) {
                    System.out.println("Причал " + jettyDao.getName() + " занят");
                }
            }
        }
    }
}
