package ru.mtsbank.port.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "jetty")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class Jetty extends BaseEntity {

    @NotNull
    @Column(name = "ships_num", nullable = false)
    private Short shipsNum;

    @NotNull
    @Column(nullable = false)
    private Integer capacity;
}