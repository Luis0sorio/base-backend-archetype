package es.osorio.base.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity{

  // Hereda id, version y campos de auditoría de BaseEntity.

  @Column(name = "username", nullable = false, unique = true, length = 50)
  private String username;

  @Column(name = "password", nullable = false)
  private String password; // Contraseña encriptada

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "enabled", nullable = false)
  private boolean enabled = true; // Control de acceso

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id")
  )
  private Set<Rol> roles = new HashSet<>(); // Evita roles duplicados
}
