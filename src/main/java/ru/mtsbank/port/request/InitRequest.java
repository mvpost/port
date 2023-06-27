package ru.mtsbank.port.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InitRequest {
    public String name;
    public String type;
    @JsonCreator
    public InitRequest(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type) {
        this.name = name;
        this.type = type;
    }
}