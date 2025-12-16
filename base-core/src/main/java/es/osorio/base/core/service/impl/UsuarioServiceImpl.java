package es.osorio.base.core.service.impl;

import es.osorio.base.core.domain.Rol;
import es.osorio.base.core.domain.RolTipo;
import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.repository.RolRepository;
import es.osorio.base.core.repository.UsuarioRepository;
import es.osorio.base.core.service.UsuarioService;

import java.util.Optional;

/**
 * Implementación del servicio de Usuario.
 * - Extiende de BaseServiceImpl<Usuario, Long>, heredando operaciones CRUD genéricas.
 * - Implementa la interfaz UsuarioService, añadiendo lógica de negocio específica.
 * - Utiliza inyección por constructor para recibir los repositorios necesarios.
 */
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final RolRepository rolRepository;

  // Inyección por constructor
  public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
    super(usuarioRepository);  // Se pasa el repositorio al servicio base.
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
  }

  @Override
  public Optional<Usuario> findByUsername(String username) {
    return usuarioRepository.findByUsername(username);
  }

  @Override
  public Usuario createUser(Usuario usuario) {

    if (usuarioRepository.existsByUsername(usuario.getUsername())) {
      throw new IllegalArgumentException("El nombre de usuario ya existe.");
    }

    Rol userRole = rolRepository.findByTipo(RolTipo.ROL_USER)
      .orElseThrow(() -> new IllegalStateException("Rol por defecto no encontrado."));

    usuario.getRoles().add(userRole);
    usuario.setEnabled(true);

    return usuarioRepository.save(usuario);
  }
}
