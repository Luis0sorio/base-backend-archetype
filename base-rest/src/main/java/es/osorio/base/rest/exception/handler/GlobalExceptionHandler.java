package es.osorio.base.rest.exception.handler;

import es.osorio.base.rest.exception.ErrorResponseDto;
import es.osorio.base.core.exception.BaseException;
import es.osorio.base.core.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Manejador global de excepciones para la capa REST.
 * - @RestControllerAdvice permite interceptar excepciones lanzadas en cualquier controlador REST y devolver una respuesta uniforme.
 * - Devuelve siempre un objeto ErrorResponseDto con información estructurada:
 *      * timestamp -> momento del error.
 *      * status    -> código HTTP.
 *      * error     -> descripción estándar del estado HTTP.
 *      * message   -> mensaje detallado del error.
 *      * path      -> URI de la petición que provocó el error.
 * El método privado buildError centraliza la construcción de la respuesta.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Maneja errores de validación en DTOs (ej. CreateUsuarioDto).
   * Devuelve el primer mensaje de error encontrado.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> handleValidation(
    MethodArgumentNotValidException ex,
    HttpServletRequest request
  ) {

    String message = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(e -> e.getField() + ": " + e.getDefaultMessage())
      .findFirst()
      .orElse("Error de validación");

    return buildError(HttpStatus.BAD_REQUEST, message, request);
  }

  /**
   * Maneja errores de recurso no encontrado.
   * Devuelve un 404 con el mensaje de la excepción.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleNotFound(
    ResourceNotFoundException ex,
    HttpServletRequest request
  ) {
    return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
  }

  /**
   * Maneja errores de negocio definidos en BaseException.
   * Devuelve un 400 con el mensaje de la excepción.
   */
  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponseDto> handleBusiness(
    BaseException ex,
    HttpServletRequest request
  ) {
    return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  /**
   * Maneja cualquier otro error inesperado.
   * Devuelve un 500 con un mensaje genérico.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleGeneric(
    Exception ex,
    HttpServletRequest request
  ) {
    return buildError(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "Error interno del servidor",
      request
    );
  }

  /**
   * Construye la respuesta de error estándar.
   * @param status  código HTTP a devolver.
   * @param message mensaje descriptivo del error.
   * @param request petición original que provocó el error.
   * @return ResponseEntity con ErrorResponseDto.
   */
  private ResponseEntity<ErrorResponseDto> buildError(
    HttpStatus status,
    String message,
    HttpServletRequest request
  ) {
    return ResponseEntity.status(status).body(
      new ErrorResponseDto(
        LocalDateTime.now(),
        status.value(),
        status.getReasonPhrase(),
        message,
        request.getRequestURI()
      )
    );
  }
}
