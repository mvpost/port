package ru.mtsbank.port.mapper;

import org.mapstruct.Mapper;
import ru.mtsbank.port.dto.FishDto;
import ru.mtsbank.port.dto.FishRequestDto;

@Mapper(componentModel = "spring")
public interface FishMapper {
    FishDto map(FishRequestDto request);
}
