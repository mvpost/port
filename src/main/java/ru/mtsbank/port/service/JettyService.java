package ru.mtsbank.port.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.model.JettyModel;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.repository.JettyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
public class JettyService {
    @Autowired
    private JettyRepository jettyRepository;
    private final List<JettyModel> jettyModelList = Collections.synchronizedList(new ArrayList<>());

    private void initList() {
        List<Jetty> jettyList = jettyRepository.findAll();
        for (Jetty jetty : jettyList) {
            JettyModel jettyModel = new JettyModel();
            jettyModel.setName(jetty.getName());
            jettyModel.setMaxShips(jetty.getShipsNum());
            jettyModel.setMaxCapacity(jetty.getCapacity());
            jettyModelList.add(jettyModel);
        }
    }

    private synchronized void addToList(String jettyName, Integer capacity) {
        for (JettyModel jettyModel : jettyModelList) {
            if (jettyModel.getName().equals(jettyName)) {
                int index = jettyModelList.indexOf(jettyModel);
                jettyModel.setShipsCount(jettyModel.getShipsCount() + 1);
                jettyModel.setCapacity(jettyModel.getCapacity() + capacity);
                jettyModelList.set(index, jettyModel);
            }
        }
    }

    private synchronized void deleteFromList(String jettyName, Integer capacity){
        for (JettyModel jettyModel : jettyModelList) {
            if (jettyModel.getName().equals(jettyName)) {
                int index = jettyModelList.indexOf(jettyModel);
                jettyModel.setShipsCount(jettyModel.getShipsCount() - 1);
                jettyModel.setCapacity(jettyModel.getCapacity() - capacity);
                jettyModelList.set(index, jettyModel);
            }
        }
    }

    public List<JettyModel> readAll() {
        return jettyModelList;
    }

    public void addShip(String shipName, Integer capacity) throws Exception {
        int lockedJettyCount = 0;

        if (jettyModelList.isEmpty()) { initList(); }

        for (JettyModel jettyModel : jettyModelList) {
            if (jettyModel.getShipsCount() < jettyModel.getMaxShips() &&
                    jettyModel.getCapacity() < jettyModel.getMaxCapacity()) {
                addToList(jettyModel.getName(), capacity);
                log.info("Корабль " +  shipName + " причалил к " + jettyModel.getName());

                Thread.sleep(1000);

                deleteFromList(jettyModel.getName(), capacity);
                log.info("Корабль " +  shipName + " освободил причал " + jettyModel.getName());

                break;
            } else {
                log.info("Причал " + jettyModel.getName() + " занят");
                if (++lockedJettyCount == jettyModelList.size()) {
                    throw new Exception("Все причалы заняты");
                }
            }
        }
    }
}
