package ru.mtsbank.port.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FishDto {

    private String name;

    private Float count;

    private Float cost;
}