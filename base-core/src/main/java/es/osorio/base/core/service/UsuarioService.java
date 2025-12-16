package es.osorio.base.core.service;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Interfaz de servicio para la entidad Usuario.
 * - Extiende de BaseService<Usuario, Long>, heredando operaciones CRUD genéricas.
 * - Define métodos adicionales específicos de la lógica de negocio de Usuario.
 * Esta interfaz será implementada por UsuarioServiceImpl, que conectará con UsuarioRepository y añadirá la lógica necesaria.
 */
public interface UsuarioService  extends BaseService<Usuario, Long> {

  Optional<Usuario> findByUsername(String username); // Busca un usuario por su nombre de usuario.
  Usuario createUser(Usuario usuario); // Crea un nuevo usuario.
  Page<Usuario> search(UsuarioFilterDto filter);
}
