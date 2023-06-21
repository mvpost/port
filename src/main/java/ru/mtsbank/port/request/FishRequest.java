package ru.mtsbank.port.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FishRequest {
    public String name;
    public Float count;
    public Float cost;
    @JsonCreator
    public FishRequest(
            @JsonProperty("name") String name,
            @JsonProperty("count") Float count,
            @JsonProperty("cost") Float cost) {
        this.name = name;
        this.count = count;
        this.cost = cost;
    }
}
