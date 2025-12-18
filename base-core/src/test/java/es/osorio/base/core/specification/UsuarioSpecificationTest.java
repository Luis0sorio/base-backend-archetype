package es.osorio.base.core.specification;

import es.osorio.base.core.config.CoreJpaTestConfig;
import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoreJpaTestConfig.class)
@Transactional
@Rollback
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

    List<Usuario> result = usuarioRepository.findAll(UsuarioSpecification.build(filter));

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getUsername()).isEqualTo("admin");
  }

  @Test
  void shouldReturnEmptyWhenNoMatch() {
    usuarioRepository.save(new Usuario("admin", "admin@test.com", "pwd"));

    UsuarioFilterDto filter = new UsuarioFilterDto();
    filter.setUsername("xyz");

    List<Usuario> result = usuarioRepository.findAll(
      UsuarioSpecification.build(filter)
    );

    assertThat(result).isEmpty();
  }
}
