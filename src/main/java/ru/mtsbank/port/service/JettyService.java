package ru.mtsbank.port.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.repository.JettyRepository;

import java.util.List;

@Service
public class JettyService {
    @Autowired
    private JettyRepository jettyRepository;
    /*@PersistenceContext
    private EntityManager entityManager;*/

    public List<Jetty> readAll() {
        return jettyRepository.findAll();
    }

    public Jetty read(int id) { return jettyRepository.getReferenceById(id); }

    public Jetty getByName(String name) { return jettyRepository.findByName(name); }

    public void addShip(String name, Integer capacity) {
        jettyRepository.setCurrentShipNumber(name);
        jettyRepository.setCurrentCapacity(name, capacity);
        System.out.println(jettyRepository.getCurrentShipNumber(name) + " " +
                jettyRepository.getCurrentCapacity(name));
    }

}