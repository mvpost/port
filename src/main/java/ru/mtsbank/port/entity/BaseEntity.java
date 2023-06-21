package ru.mtsbank.port.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @Column
    @SequenceGenerator(name = "IdSeq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdSeq")
    protected Integer id;

    @CreationTimestamp
    @Column
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    protected LocalDateTime updatedAt;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false)
    protected String name;
}
