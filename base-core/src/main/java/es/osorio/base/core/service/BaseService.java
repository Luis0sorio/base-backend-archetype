package es.osorio.base.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz base genérica de servicio para todas las entidades.
 * - <T, ID>:
 *      T -> tipo de la entidad gestionada.
 *      ID -> tipo del identificador de la entidad (debe ser Serializable).
 * - Define operaciones CRUD comunes que pueden ser reutilizadas por servicios específicos.
 */
public interface BaseService<T, ID extends Serializable> {

  // Guarda una entidad en la base de datos.
  T save (T entity);
  // Busca una entidad.
  Optional<T> findById(ID id);
  // Recupera todas las entidades de la tabla correspondiente.
  List<T> findAll();
  // Elimina una entidad.
  void deleteById(ID id);
  // Comprueba si existe una entidad.
  boolean existsById(ID id);
}
