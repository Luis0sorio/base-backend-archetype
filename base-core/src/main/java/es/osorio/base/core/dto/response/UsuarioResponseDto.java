package es.osorio.base.core.dto.response;

import java.util.Set;

/**
 * DTO de respuesta para Usuario.
 * Se devuelve al cliente en las operaciones REST.
 */
public record UsuarioResponseDto (
  Long id,
  String username,
  String email,
  boolean activo,
  Set<String> roles
){}
