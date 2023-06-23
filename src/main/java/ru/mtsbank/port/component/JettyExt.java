package ru.mtsbank.port.component;

import lombok.Getter;
import lombok.Setter;
import ru.mtsbank.port.entity.Jetty;

@Getter
@Setter
public class JettyExt extends Jetty {
    private Integer currentCapacity = 0;
    private Integer currentShipNumber = 0;
}
