package es.osorio.base.core.service;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.dto.request.CreateUsuarioDto;
import es.osorio.base.core.dto.request.UpdateUsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Interfaz de servicio para la entidad Usuario.
 * - Extiende de BaseService<Usuario, Long>, heredando operaciones CRUD genéricas.
 * - Define métodos adicionales específicos de la lógica de negocio de Usuario.
 * Esta interfaz será implementada por UsuarioServiceImpl, que conectará con UsuarioRepository y añadirá la lógica necesaria.
 */
public interface UsuarioService  extends BaseService<Usuario, Long> {

  Optional<Usuario> findById(Long id);
  Optional<Usuario> findByUsername(String username); // Busca un usuario por su nombre de usuario.
  Usuario create(CreateUsuarioDto dto); // Crea un nuevo usuario.
  Usuario update(Long id, UpdateUsuarioDto dto);
  Page<Usuario> search(UsuarioFilterDto filter, Pageable pageable);
  void delete(Long id);
}
