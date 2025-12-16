package es.osorio.base.core.specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * Clase base para construir Specifications reutilizables.
 * - Contiene métodos estáticos que permiten generar condiciones dinámicas en consultas JPA basadas en parámetros opcionales.
 * - Si el valor recibido es nulo o vacío, se devuelve `null` para que Spring Data JPA ignore esa condición en la composición de Specifications.
 * - Esto permite construir filtros flexibles sin necesidad de comprobar manualmente cada parámetro en el servicio.
 */
public abstract class BaseSpecification {

  /**
   * Genera una condición de igualdad si el valor está presente.
   * @param field nombre del atributo en la entidad
   * @param value valor a comparar
   * @param <T>   tipo de la entidad
   * @return Specification que aplica cb.equal(...) si el valor no es nulo, o null si el valor es nulo (condición ignorada)
   */
  protected static <T> Specification<T> equalsIfPresent(String field, Object value) {
    return (root, query, cb) ->
      value == null
        ? null // cb.conjunction()
        : cb.equal(root.get(field), value);
  }

  /**
   * Genera una condición LIKE case-insensitive si el valor está presente.
   * @param field nombre del atributo en la entidad
   * @param value valor a buscar (se aplicará %value%)
   * @param <T>   tipo de la entidad
   * @return Specification que aplica cb.like(cb.lower(...)) si el valor no es nulo ni vacío, o null si el valor es nulo/vacío (condición ignorada)
   */
  protected static <T> Specification<T> likeIgnoreCaseIfPresent(String field, String value) {
    return (root, query, cb) ->
      value == null || value.isBlank()
        ? null // cb.conjunction()
        : cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
  }
}
