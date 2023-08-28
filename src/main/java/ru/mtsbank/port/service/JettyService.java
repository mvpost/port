package ru.mtsbank.port.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mtsbank.port.exception.JettyBusyException;
import ru.mtsbank.port.model.JettyModel;
import ru.mtsbank.port.model.JettyModelCacheable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class JettyService {

    private final JettyModelCacheable jettyModelCacheable;
    //private List<JettyModel> jettyModelList = Collections.synchronizedList(new ArrayList<>());

    private synchronized void takeJetty(String jettyName, Integer capacity) throws Exception {
        JettyModel jettyModel = jettyModelCacheable.fetch().stream()
                .filter(j -> j.getName().equals(jettyName))
                .findAny().orElseThrow(() -> new Exception("Причал " + jettyName + " не найден"));
        if (jettyModel.getCurShipsCount() >= jettyModel.getMaxShipsCount()
                || jettyModel.getCurCapacity() + capacity > jettyModel.getMaxCapacity())
            throw new Exception("Причал " + jettyName + " не может принять судно");
        jettyModel.setCurShipsCount(jettyModel.getCurShipsCount() + 1);
        jettyModel.setCurCapacity(jettyModel.getCurCapacity() + capacity);
    }

    private synchronized void releaseJetty(String jettyName, Integer capacity) throws Exception {
        JettyModel jettyModel = jettyModelCacheable.fetch().stream()
                .filter(j -> j.getName().equals(jettyName))
                .findAny().orElseThrow(() -> new Exception("Причал " + jettyName + " не найден"));
        if (jettyModel.getCurShipsCount().equals(0)
                || jettyModel.getCurCapacity() - capacity < 0)
            throw new Exception("Причал " + jettyName + " пуст");
        jettyModel.setCurShipsCount(jettyModel.getCurShipsCount() - 1);
        jettyModel.setCurCapacity(jettyModel.getCurCapacity() - capacity);
    }

    private void downloadFile(MultipartFile file) throws IOException, InterruptedException {
        File convertFile = new File("/var/tmp/" + file.getName());
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        Thread.sleep(2000);
    }

    public List<JettyModel> readAll() {
        //jettyModelList = jettyModelCacheable.fetch();

        return jettyModelCacheable.fetch();
    }

    public String getFreeJettyName(Integer capacity) {
        String freeJettyName = null;
        for (JettyModel jettyModel : jettyModelCacheable.fetch()) {
            log.info("Состояние причала %s: кол.-во кораблей: %s, загрузка: %s"
                    .formatted(jettyModel.getName(), jettyModel.getCurShipsCount(), jettyModel.getCurCapacity()));
            if (jettyModel.getCurShipsCount() < jettyModel.getMaxShipsCount() &&
                    jettyModel.getCurCapacity() + capacity <= jettyModel.getMaxCapacity()) {
                freeJettyName = jettyModel.getName();
                break;
            }
        }
        if (freeJettyName != null) {
            return freeJettyName;
        } else {
            throw new JettyBusyException();
        }
    }

    public void requestJetty(String shipName, Integer capacity,
                            String jettyName, MultipartFile file) throws Exception {
        takeJetty(jettyName, capacity);
        log.info("Корабль %s ёмкостью %s причалил к %s".formatted(shipName, capacity, jettyName));

        downloadFile(file);

        releaseJetty(jettyName, capacity);
        log.info("Корабль %s освободил причал %s".formatted(shipName, jettyName));
    }
}
