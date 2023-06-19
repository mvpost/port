package ru.mtsbank.port.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.repository.CountryRepository;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Country country) {
        countryRepository.save(country);
    }

    @Override
    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country read(int id) {
        return countryRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Country country, int id) {
        if (countryRepository.existsById(id)) {
            countryRepository.save(country);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Country getRandomCountry(String countryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot);
        Predicate predicateName = criteriaBuilder.notEqual(countryRoot.get("name"), countryName);
        criteriaQuery.where(predicateName);
        criteriaQuery.orderBy(criteriaBuilder.desc(countryRoot.get("name")));
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(0)
                .setMaxResults(1)
                .getSingleResult();
    }
}