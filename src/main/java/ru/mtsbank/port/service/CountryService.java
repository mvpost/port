package ru.mtsbank.port.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Создает новую страну
     * @param country - страна для создания
     */
    public void create(Country country) {
        countryRepository.save(country);
    }

    /**
     * Возвращает список всех имеющихся стран
     * @return список стран
     */
    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    /**
     * Возвращает страну по её ID
     * @param id - ID страны
     * @return - объект страны с заданным ID
     */
    public Country read(int id) {
        return countryRepository.getReferenceById(id);
    }

    /**
     * Обновляет страну с заданным ID,
     * в соответствии с переданной страной
     * @param country - страна, где нужно обновить данные
     * @param id - id страны, которую нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    public boolean update(Country country, int id) {
        if (countryRepository.existsById(id)) {
            countryRepository.save(country);
            return true;
        }

        return false;
    }

    /**
     * Удаляет страну с заданным ID
     * @param id - id страны, которую нужно удалить
     * @return - true если страна была удалена, иначе false
     */
    public boolean delete(int id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Возвращает новую страну
     * @return название страны
     */
    public Country getRandomCountry(String countryName) {
        return countryRepository.findDistinctFirstByNameNotLike(countryName);
    }
}