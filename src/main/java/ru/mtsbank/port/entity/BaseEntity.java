package ru.mtsbank.port.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @Column
    protected Integer id;

    @CreationTimestamp
    @Column
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    protected LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Hibernate.getClass(this), id);
    }
}
