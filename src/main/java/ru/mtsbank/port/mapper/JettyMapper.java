package ru.mtsbank.port.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import ru.mtsbank.port.dto.CountryDto;
import ru.mtsbank.port.dto.JettyDto;
import ru.mtsbank.port.entity.Country;
import ru.mtsbank.port.model.JettyModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JettyMapper {
    @IterableMapping(elementTargetType = JettyDto.class)
    List<JettyDto> map(List<JettyModel> jetties);
}
