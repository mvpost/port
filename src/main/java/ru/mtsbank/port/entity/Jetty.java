package ru.mtsbank.port.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "jetty")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class Jetty extends BaseEntity {

    @NotNull
    @Positive
    @Column(name = "ships_num", nullable = false)
    private Short shipsNum;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer capacity;

}