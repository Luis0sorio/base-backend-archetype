package es.osorio.base.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol { // NO hereda de BaseEntity. Es una entidad estática, sin auditoría.

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING) // Se guarda como texto en la base de datos (no como ordinal).
  @Column(name = "tipo", nullable = false, unique = true)
  private RolTipo tipo;
}
