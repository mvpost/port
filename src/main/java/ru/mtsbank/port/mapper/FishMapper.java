package ru.mtsbank.port.mapper;

import org.mapstruct.Mapper;
import ru.mtsbank.port.dto.FishDto;
import ru.mtsbank.port.request.FishRequest;

@Mapper(componentModel = "spring")
public interface FishMapper {
    FishDto map(FishRequest request);
}
