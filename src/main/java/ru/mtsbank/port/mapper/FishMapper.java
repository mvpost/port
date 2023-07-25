package ru.mtsbank.port.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mtsbank.port.dto.FishDto;
import ru.mtsbank.port.entity.Fish;

import java.util.List;
@Mapper(componentModel = "spring")
public interface FishMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FishDto map(Fish entity);

    @IterableMapping(elementTargetType = FishDto.class)
    List<FishDto> map(List<Fish> fishes);
}
