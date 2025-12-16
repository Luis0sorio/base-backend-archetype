package es.osorio.base.core.service.impl;

import es.osorio.base.core.repository.base.BaseRepository;
import es.osorio.base.core.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Implementación genérica de la interfaz BaseService.
 * - <T, ID>:
 *      T -> tipo de la entidad gestionada.
 *      ID -> tipo del identificador (debe ser Serializable).
 * - Utiliza BaseRepository<T, ID> para delegar las operaciones CRUD.
 * - @Transactional -> asegura la gestión de transacciones en todas las operaciones de servicio.
 * - Las operaciones de lectura se marcan como readOnly para optimizar el rendimiento y evitar bloqueos innecesarios.
 * Esta clase debe ser extendida por servicios concretos que podrán añadir lógica de negocio específica.
 */
@Transactional
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

  // Inyección por constructor
  protected final BaseRepository<T, ID> repository;
  // Obliga a las subclases a proporcionar un repositorio concreto
  protected BaseServiceImpl(BaseRepository<T, ID> repository) {
    this.repository = repository;
  }

  @Override
  public T save(T entity) {
    return repository.save(entity); // Inserta o actualiza la entidad.
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<T> findById(ID id) {
    return repository.findById(id); // Busca por ID.
  }

  @Override
  @Transactional(readOnly = true)
  public List<T> findAll() {
    return repository.findAll(); // // Recupera todas las entidades.
  }

  @Override
  public void deleteById(ID id) {
    repository.deleteById(id); // Elimina por ID.
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsById(ID id) {
    return repository.existsById(id); // Comprueba existencia por ID.
  }
}
