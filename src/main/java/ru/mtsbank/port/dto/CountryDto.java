package ru.mtsbank.port.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDto {

    protected Integer id;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    private String name;

    @JsonProperty("latitude")
    private Float lat;

    @JsonProperty("longitude")
    private Float lon;
}
