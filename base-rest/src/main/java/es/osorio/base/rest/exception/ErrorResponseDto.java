package es.osorio.base.rest.exception;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para representar errores en la API REST.
 * - Se utiliza en el GlobalExceptionHandler para devolver información estructurada al cliente cuando ocurre una excepción.
 * - Permite mantener un formato uniforme en todas las respuestas de error.
 * Campos:
 *   - timestamp -> momento exacto en que ocurrió el error.
 *   - status    -> código HTTP asociado al error (ej. 400, 404, 500).
 *   - error     -> descripción estándar del estado HTTP (ej. "Bad Request", "Not Found").
 *   - message   -> mensaje detallado del error (ej. validación fallida, recurso no encontrado).
 *   - path      -> URI de la petición que provocó el error.
 * Ejemplo de respuesta JSON:
 * {
 *   "timestamp": "2025-12-16T13:56:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Usuario no encontrado con identificador: 42",
 *   "path": "/api/usuarios/42"
 * }
 */
public record ErrorResponseDto(
  LocalDateTime timestamp,
  int status,
  String error,
  String message,
  String path
) {}
