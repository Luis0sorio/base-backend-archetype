package es.osorio.base.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // Clase base cuyos atributos deben ser heredados por las entidades JPA que la extienden.
@EntityListeners(AuditingEntityListener.class) // Rellena automáticamente campos de auditoría.
public abstract class BaseEntity implements Serializable { // Serializable permite que la entidad pueda viajar por red, guardarse en cachés o sesiones.

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Identificador único

  @Version // Cada vez que una entidad se actualiza, el valor se incrementa automáticamente.
  private Long version; // Optimistic Locking

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;  // Fecha de creación, asignada automáticamente, nunca se actualiza.

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt; // Fecha de última modificación, se actualiza automáticamente cuando la entidad cambia.
}
