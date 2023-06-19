package ru.mtsbank.port.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "country")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class Country extends BaseEntity {
    @Id
    @Column
    @SequenceGenerator(name = "countryIdSeq", sequenceName = "country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countryIdSeq")
    private Integer id;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Float lat;

    @NotNull
    @Column(nullable = false)
    private Float lon;
}
