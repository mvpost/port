package ru.mtsbank.port.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mtsbank.port.model.JettyModel;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.repository.JettyRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private void downloadFile(MultipartFile file) throws IOException, InterruptedException {
        File convertFile = new File("/var/tmp/" + file.getName());
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        Thread.sleep(1000);
    }

    public List<JettyModel> readAll() {
        return jettyModelList;
    }

    public void addShip(String shipName, Integer capacity, MultipartFile file) throws Exception {
        int lockedJettyCount = 0;

        if (jettyModelList.isEmpty()) { initList(); }

        for (JettyModel jettyModel : jettyModelList) {
            if (jettyModel.getShipsCount() < jettyModel.getMaxShips() &&
                    jettyModel.getCapacity() < jettyModel.getMaxCapacity()) {
                addToList(jettyModel.getName(), capacity);
                log.info("Корабль %s причалил к %s".formatted(shipName, jettyModel.getName()));

                downloadFile(file);

                deleteFromList(jettyModel.getName(), capacity);
                log.info("Корабль %s освободил причал %s".formatted(shipName, jettyModel.getName()));
                break;
            } else {
                log.info("Причал %s занят".formatted(jettyModel.getName()));
                if (++lockedJettyCount == jettyModelList.size()) {
                    throw new Exception("Все причалы заняты");
                }
            }
        }
    }
}
