package ru.mtsbank.port.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FishRequest {
    public String name;
    public Float count;
    @JsonCreator
    public FishRequest(
            @JsonProperty("name") String name,
            @JsonProperty("count") Float count) {
        this.name = name;
        this.count = count;
    }
}
