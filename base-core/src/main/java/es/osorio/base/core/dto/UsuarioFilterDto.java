package es.osorio.base.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO de filtros específicos para la entidad Usuario.
 * - Extiende de BaseFilterDto para heredar parámetros de paginación y ordenación.
 * - Añade filtros propios del dominio Usuario:
 *   - username: búsqueda parcial por nombre de usuario.
 *   - email: búsqueda parcial por email.
 *   - activo: filtro booleano por estado de activación.
 * - Se utiliza en servicios y controladores para construir Specifications dinámicas.
 */
@Getter
@Setter
public class UsuarioFilterDto extends BaseFilterDto {

  private String username;
  private String email;
  private Boolean activo;

}
