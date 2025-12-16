package es.osorio.base.core.specification;


import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class UsuarioSpecificationTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Test
  void shouldFilterByUsername() {

    Usuario u1 = new Usuario("admin", "admin@test.com", "pwd");
    Usuario u2 = new Usuario("user", "user@test.com", "pwd");

    usuarioRepository.saveAll(List.of(u1, u2));

    UsuarioFilterDto filter = new UsuarioFilterDto();
    filter.setUsername("adm");

    Specification<Usuario> spec = UsuarioSpecification.build(filter);

    List<Usuario> result = usuarioRepository.findAll(spec);

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getUsername()).isEqualTo("admin");
  }

  @Test
  void shouldFilterByActivo() {

    Usuario activo = new Usuario("activo", "a@test.com", "pwd");
    Usuario inactivo = new Usuario("inactivo", "i@test.com", "pwd");
    inactivo.desactivar();

    usuarioRepository.saveAll(List.of(activo, inactivo));

    UsuarioFilterDto filter = new UsuarioFilterDto();
    filter.setActivo(false);

    List<Usuario> result =
      usuarioRepository.findAll(UsuarioSpecification.build(filter));

    assertThat(result).hasSize(1);
    assertThat(result.get(0).isActivo()).isFalse();
  }
}
