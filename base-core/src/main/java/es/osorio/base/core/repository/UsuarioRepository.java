package es.osorio.base.core.repository;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.repository.base.BaseRepository;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

  Optional<Usuario> findByUsername(String username); // Devuelve el usuario si existe.
  boolean existsByUsername(String username); // // Comprueba existencia.
}
