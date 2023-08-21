package ru.mtsbank.port.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mtsbank.port.dto.JettyDto;
import ru.mtsbank.port.entity.Jetty;
import ru.mtsbank.port.model.JettyModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JettyMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "shipsNum", target = "maxShipsCount")
    @Mapping(source = "capacity", target = "maxCapacity")
    JettyModel mapModel(Jetty entity);

    List<JettyModel> mapModel(List<Jetty> entities);

    @IterableMapping(elementTargetType = JettyDto.class)
    List<JettyDto> map(List<JettyModel> jetties);
}
