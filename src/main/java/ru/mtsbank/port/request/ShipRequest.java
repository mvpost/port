package ru.mtsbank.port.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShipRequest {
    public String name;
    public Integer capacity;
    @JsonCreator
    public ShipRequest(
            @JsonProperty("name") String name,
            @JsonProperty("capacity") Integer capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
