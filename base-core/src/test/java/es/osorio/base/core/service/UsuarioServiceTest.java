package es.osorio.base.core.service;

import es.osorio.base.core.config.CoreJpaTestConfig;
import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoreJpaTestConfig.class)
@Transactional
class UsuarioServiceTest {

  @Autowired
  UsuarioService usuarioService;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Test
  void searchShouldReturnPagedResult() {
    usuarioRepository.save(new Usuario("admin", "admin@test.com", "pwd"));
    usuarioRepository.save(new Usuario("user", "user@test.com", "pwd"));

    UsuarioFilterDto filter = new UsuarioFilterDto();
    filter.setUsername("adm");

    Page<Usuario> result = usuarioService.search(
      filter,
      PageRequest.of(0, 10)
    );

    assertThat(result.getContent()).hasSize(1);
  }
}