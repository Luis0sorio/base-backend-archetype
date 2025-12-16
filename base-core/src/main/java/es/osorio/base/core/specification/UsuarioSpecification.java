package es.osorio.base.core.specification;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import org.springframework.data.jpa.domain.Specification;

public final class UsuarioSpecification extends BaseSpecification {

  private UsuarioSpecification() {}

  public static Specification<Usuario> build(UsuarioFilterDto filter) {
    return Specification
      .where(usernameContains(filter.getUsername()))
      .and(emailContains(filter.getEmail()))
      .and(activoEquals(filter.getActivo()));
  }

  private static Specification<Usuario> usernameContains(String username) {
    return likeIgnoreCaseIfPresent("username", username);
  }

  private  static Specification<Usuario> emailContains(String email) {
    return likeIgnoreCaseIfPresent("email", email);
  }

  private  static Specification<Usuario> activoEquals(Boolean activo) {
    return equalsIfPresent("activo", activo);
  }

}
