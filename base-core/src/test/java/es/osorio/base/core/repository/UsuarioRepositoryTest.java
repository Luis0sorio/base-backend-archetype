package es.osorio.base.core.repository;

import es.osorio.base.core.config.CoreJpaTestConfig;
import es.osorio.base.core.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoreJpaTestConfig.class)
@Transactional
class UsuarioRepositoryTest {

  @Autowired
  UsuarioRepository repository;

  @Test
  void shouldSaveAndFindById() {
    Usuario u = new Usuario("admin", "admin@test.com", "pwd");

    Usuario saved = repository.save(u);

    assertThat(repository.findById(saved.getId())).isPresent();
  }
}