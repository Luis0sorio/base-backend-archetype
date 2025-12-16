package es.osorio.base.core.domain;

import es.osorio.base.core.domain.base.BaseEntity;
import es.osorio.base.core.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "usuario",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
  })
public class Usuario extends BaseEntity {

  // Hereda id, version y campos de auditoría de BaseEntity.

  @Column(name = "username", nullable = false, length = 50)
  private String username;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "password", nullable = false)
  private String password; // Contraseña encriptada

  @Column(name = "enabled", nullable = false)
  private boolean activo = true; // Control de acceso

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id")
  )
  private Set<Rol> roles = new HashSet<>(); // Evita roles duplicados

  protected Usuario() {}

  public Usuario(String username, String email, String password) {
    validar(username, email, password);
    this.username = username;
    this.email = email;
    this.password = password;
  }

  private void validar(String username, String email, String password) {
    if (username == null || username.isBlank()) {
      throw new BusinessException("El username es obligatorio");
    }
    if (email == null || email.isBlank()) {
      throw new BusinessException("El email es obligatorio");
    }
    if (password == null || password.isBlank()) {
      throw new BusinessException("La contraseña es obligatoria");
    }
  }

  public void cambiarEmail(String nuevoEmail) {
    if (nuevoEmail == null || nuevoEmail.isBlank()) {
      throw new BusinessException("Email inválido");
    }
    this.email = nuevoEmail;
  }

  public void cambiarUsername(String nuevoUsername) {
    if (nuevoUsername == null || nuevoUsername.isBlank()) {
      throw new BusinessException("Nombre de usuario inválido");
    }
    this.username = nuevoUsername;
  }

  public void desactivar() {
    this.activo = false;
  }
  public void activar() {
    this.activo = true;
  }

  public void asignarPasswordHash(String hash) {
    this.password = hash;
  }
}
