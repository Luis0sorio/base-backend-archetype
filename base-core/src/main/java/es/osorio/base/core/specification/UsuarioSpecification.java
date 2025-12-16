package es.osorio.base.core.specification;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import org.springframework.data.jpa.domain.Specification;

/**
 * Clase de utilidad para construir Specifications de la entidad Usuario.
 * - Implementada como utility estática
 * - Encapsula la lógica de filtrado dinámico a partir de un UsuarioFilterDto.
 * - Permite componer condiciones opcionales (username, email, activo) de forma limpia.
 */
public final class UsuarioSpecification extends BaseSpecification {

  // Constructor privado para evitar instanciación.
  private UsuarioSpecification() {}

  /**
   * Construye un Specification<Usuario> a partir de los filtros recibidos.
   * @param filter DTO con parámetros opcionales de búsqueda
   * @return Specification compuesto con las condiciones dinámicas
   */
  public static Specification<Usuario> build(UsuarioFilterDto filter) {
    return Specification
      .where(usernameContains(filter.getUsername()))
      .and(emailContains(filter.getEmail()))
      .and(activoEquals(filter.getActivo()));
  }

  /**
   * Filtro por username (LIKE case-insensitive).
   * Ignorado si el valor es nulo o vacío.
   */
  private static Specification<Usuario> usernameContains(String username) {
    return likeIgnoreCaseIfPresent("username", username);
  }

  /**
   * Filtro por email (LIKE case-insensitive).
   * Ignorado si el valor es nulo o vacío.
   */
  private  static Specification<Usuario> emailContains(String email) {
    return likeIgnoreCaseIfPresent("email", email);
  }

  /**
   * Filtro por estado activo (igualdad booleana).
   * Ignorado si el valor es nulo.
   */
  private  static Specification<Usuario> activoEquals(Boolean activo) {
    return equalsIfPresent("activo", activo);
  }

}
