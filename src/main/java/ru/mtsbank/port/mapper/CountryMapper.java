package ru.mtsbank.port.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import ru.mtsbank.port.dto.CountryDto;
import ru.mtsbank.port.entity.Country;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto map(Country entity);
    @IterableMapping(elementTargetType = CountryDto.class)
    List<CountryDto> map(List<Country> countries);
}
