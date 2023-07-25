package ru.mtsbank.port.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mtsbank.port.dto.CountryDto;
import ru.mtsbank.port.entity.Country;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CountryDto map(Country entity);

    @IterableMapping(elementTargetType = CountryDto.class)
    List<CountryDto> map(List<Country> countries);
}
