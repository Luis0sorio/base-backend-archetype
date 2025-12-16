package es.osorio.base.core.service.impl;

import es.osorio.base.core.domain.Rol;
import es.osorio.base.core.domain.RolTipo;
import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.exception.BusinessException;
import es.osorio.base.core.exception.ResourceNotFoundException;
import es.osorio.base.core.repository.RolRepository;
import es.osorio.base.core.repository.UsuarioRepository;
import es.osorio.base.core.service.UsuarioService;

import es.osorio.base.core.specification.UsuarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación del servicio de Usuario.
 * - Extiende de BaseServiceImpl<Usuario, Long>, heredando operaciones CRUD genéricas.
 * - Implementa la interfaz UsuarioService, añadiendo lógica de negocio específica.
 * - Utiliza inyección por constructor para recibir los repositorios necesarios.
 */
@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final RolRepository rolRepository;
  private final PasswordEncoder passwordEncoder;

  // Inyección por constructor
  public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository,
                            BCryptPasswordEncoder passwordEncoder) {
    super(usuarioRepository);  // Se pasa el repositorio al servicio base.
    this.usuarioRepository = usuarioRepository;
    this.rolRepository = rolRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<Usuario> findByUsername(String username) {
    return usuarioRepository.findByUsername(username);
  }

  @Override
  public Usuario createUser(Usuario usuario) {

    if (usuarioRepository.existsByUsername(usuario.getUsername())) {
      throw new BusinessException("El nombre de usuario ya existe.");
    }

    Rol userRole = rolRepository.findByTipo(RolTipo.ROL_USER)
      .orElseThrow(() -> new ResourceNotFoundException("Rol", RolTipo.ROL_USER));

    usuario.getRoles().add(userRole);
    usuario.activar();
    usuario.asignarPasswordHash(passwordEncoder.encode(usuario.getPassword()));

    return usuarioRepository.save(usuario);
  }

  @Override
  public Page<Usuario> search(UsuarioFilterDto filter) {
    Specification<Usuario> specification = UsuarioSpecification.build(filter);

    Pageable pageable = PageRequest.of(
      filter.getPage(),
      filter.getSize(),
      Sort.by(filter.getSortDirection(), filter.getSortBy())
    );
    return usuarioRepository.findAll(specification, pageable);
  }
}
