package ru.mtsbank.port.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Fish;
import ru.mtsbank.port.repository.FishRepository;
import java.util.List;

@Service
public class FishService {
    @Autowired
    private FishRepository fishRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Возвращает список рыб
     * @return список рыб
     */
    public List<Fish> readAll() {
        return fishRepository.findAll();
    }

    /**
     * Возвращает стоимость рыбы
     * @return стоимость
     */
    public Float calcCost(String fishName, Float fishCount) {
        float fishCost = 0.00f;

        if (fishName != null && fishCount != null) {

            float fishPrice;

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Fish> criteriaQuery = criteriaBuilder.createQuery(Fish.class);
            Root<Fish> fishRoot = criteriaQuery.from(Fish.class);
            criteriaQuery.select(fishRoot);
            Predicate predicateName = criteriaBuilder.equal(fishRoot.get("name"), fishName);
            criteriaQuery.where(predicateName);
            Fish fish = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();

            fishPrice = fish.getPrice();
            fishCost = fishCount * fishPrice;
        }
        return fishCost;
    }
}
