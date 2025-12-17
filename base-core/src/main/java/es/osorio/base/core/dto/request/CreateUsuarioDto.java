package es.osorio.base.core.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para la creación de un nuevo Usuario.
 * - Se utiliza en la capa REST para recibir datos desde el cliente al realizar una petición POST de creación de usuario.
 * - Aplica validaciones con anotaciones de Jakarta Validation.
 */
public record CreateUsuarioDto (

  @NotBlank
  @Size(min = 4, max = 50)
  String username,

  @Email
  @NotBlank
  String email,

  @NotBlank
  @Size(min = 8)
  String password
) {}
