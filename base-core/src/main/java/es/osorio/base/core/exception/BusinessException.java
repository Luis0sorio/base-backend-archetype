package es.osorio.base.core.exception;

/**
 * Excepción personalizada para representar errores de lógica de negocio.
 * - Se utiliza cuando ocurre una condición inválida en las reglas de negocio,
 *   por ejemplo: intentar crear un usuario con un nombre ya existente.
 */
public class BusinessException extends BaseException {

  /**
   * Constructor con mensaje descriptivo.
   * @param message descripción del error de negocio ocurrido.
   */
  public BusinessException(String message) {
    super(message);
  }
}
