package es.osorio.base.core.dto.base;

import lombok.Getter;
import org.springframework.data.domain.Sort;

/**
 * DTO base para filtros de búsqueda con soporte de paginación y ordenación.
 * - Define parámetros comunes a cualquier consulta paginada. Se usan para el filtrado:
 *   - page: número de página (por defecto 0).
 *   - size: tamaño de página (por defecto 10).
 *   - sortBy: campo por el que se ordena (por defecto "id").
 *   - sortDirection: dirección de orden (ASC por defecto).
 * - Se utiliza como clase abstracta para ser extendida por filtros específicos de cada entidad (ej. UsuarioFilterDto).
 */
@Getter
public abstract class BaseFilterDto {

  private Integer page = 0;
  private Integer size = 10;

  private String sortBy = "id";
  private Sort.Direction sortDirection = Sort.Direction.ASC;

}
