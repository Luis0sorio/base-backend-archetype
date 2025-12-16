package es.osorio.base.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de entrada para la actualización de un Usuario existente.
 * - Se utiliza en la capa REST para recibir datos desde el cliente al realizar una petición PUT/PATCH de actualización de usuario.
 * - Aplica validaciones con anotaciones de Jakarta Validation.
 * - El campo "activo" es opcional (puede ser null), lo que permite actualizar solo el email o también el estado de activación del usuario.
 */
public record UpdateUsuarioDto(
  @NotBlank
  String email,

  Boolean activo
) {}
