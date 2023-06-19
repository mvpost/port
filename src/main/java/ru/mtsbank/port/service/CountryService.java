package ru.mtsbank.port.service;

import ru.mtsbank.port.entity.Country;

import java.util.List;

public interface CountryService {

    /**
     * Создает новую страну
     * @param country - страна для создания
     */
    void create(Country country);

    /**
     * Возвращает список всех имеющихся стран
     * @return список стран
     */
    List<Country> readAll();

    /**
     * Возвращает новую страну
     * @return название страны
     */
    Country getRandomCountry(String countryName);

    /**
     * Возвращает страну по её ID
     * @param id - ID страны
     * @return - объект страны с заданным ID
     */
    Country read(int id);

    /**
     * Обновляет страну с заданным ID,
     * в соответствии с переданной страной
     * @param country - страна, где нужно обновить данные
     * @param id - id страны, которую нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Country country, int id);

    /**
     * Удаляет страну с заданным ID
     * @param id - id страны, которую нужно удалить
     * @return - true если страна была удалена, иначе false
     */
    boolean delete(int id);

}
