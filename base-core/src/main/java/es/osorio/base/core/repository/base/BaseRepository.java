package es.osorio.base.core.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Repositorio base genérico para todas las entidades.
 * Hereda operaciones CRUD y de paginación.
 * @NoRepositoryBean Spring Data JPA no intenta instanciar directamente este repositorio como un bean.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
  // Aquí se pueden definir métodos comunes a todos los repositorios, si se necesitan.
}
