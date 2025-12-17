package es.osorio.base.core.service.impl;

import es.osorio.base.core.domain.Rol;
import es.osorio.base.core.domain.RolTipo;
import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.dto.request.CreateUsuarioDto;
import es.osorio.base.core.dto.request.UpdateUsuarioDto;
import es.osorio.base.core.exception.BusinessException;
import es.osorio.base.core.exception.ResourceNotFoundException;
import es.osorio.base.core.repository.RolRepository;
import es.osorio.base.core.repository.UsuarioRepository;
import es.osorio.base.core.service.UsuarioService;

import es.osorio.base.core.specification.UsuarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  /**
   * Busca un usuario por su ID.
   * @param id identificador del usuario
   * @return Optional con el usuario si existe
   */
  @Override
  public Optional<Usuario> findById(Long id) {
    return usuarioRepository.findById(id);
  }

  /**
   * Busca un usuario por su nombre de usuario.
   * @param username nombre de usuario
   * @return Optional con el usuario si existe
   */
  @Override
  public Optional<Usuario> findByUsername(String username) {
    return usuarioRepository.findByUsername(username);
  }

  /**
   * Crea un nuevo usuario
   * @param dto DTO de creación de usuario
   * @return Usuario persistido en la base de datos
   * @throws BusinessException si el username ya existe
   * @throws ResourceNotFoundException si el rol ROL_USER no está definido en la base de datos
   */
  @Override
  public Usuario create(CreateUsuarioDto dto) {

    if (usuarioRepository.existsByUsername(dto.username())) {
      throw new BusinessException("El nombre de usuario ya existe.");
    }

    Rol userRole = rolRepository.findByTipo(RolTipo.ROL_USER)
      .orElseThrow(() -> new ResourceNotFoundException("Rol", RolTipo.ROL_USER));

    Usuario usuario = new Usuario(
      dto.username(),
      dto.email(),
      dto.password()
    );

    usuario.getRoles().add(userRole);
    usuario.activar();
    usuario.asignarPasswordHash(passwordEncoder.encode(dto.password()));

    return usuarioRepository.save(usuario);
  }

  /**
   * Actualiza un usuario existente.
   * @param id identificador del usuario
   * @param dto DTO con los campos a actualizar
   * @return Usuario actualizado
   * @throws ResourceNotFoundException si el usuario no existe
   */
  @Override
  public Usuario update(Long id, UpdateUsuarioDto dto) {

    Usuario usuario = usuarioRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

    if (dto.username() != null) {
      usuario.cambiarUsername(dto.username());
    }

    if (dto.email() != null) {
      usuario.cambiarEmail(dto.email());
    }

    if (dto.password() != null && !dto.password().isBlank()) {
      usuario.asignarPasswordHash(passwordEncoder.encode(dto.password()));
    }

    return usuarioRepository.save(usuario);
  }

  /**
   * Busca usuarios aplicando filtros dinámicos y paginación.
   * - Construye un Specification<Usuario> a partir de UsuarioFilterDto.
   * - Aplica paginación y ordenación según los parámetros del DTO.
   * - Devuelve un Page<Usuario> con los resultados.
   * @param filter DTO con filtros opcionales (username, email, activo) y parámetros de paginación
   * @return página de usuarios filtrados
   */
  @Override
  public Page<Usuario> search(UsuarioFilterDto filter, Pageable pageable) {
    Specification<Usuario> specification = UsuarioSpecification.build(filter);
    return usuarioRepository.findAll(specification, pageable);
  }

  /**
   * Elimina un usuario por su ID.
   * @param id identificador del usuario
   * @throws ResourceNotFoundException si el usuario no existe
   */
  @Override
  public void delete(Long id) {
    Usuario usuario = usuarioRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
    usuarioRepository.delete(usuario);
  }

}
