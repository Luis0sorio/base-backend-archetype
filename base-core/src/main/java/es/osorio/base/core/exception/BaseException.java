package es.osorio.base.core.exception;

/**
 * Clase base abstracta para las excepciones personalizadas.
 * - Extiende de RuntimeException (unchecked), lo que evita la obligación de declararlas en las firmas de métodos.
 * - Clase padre que otras excepciones específicas deben extender.
 * - Centraliza la jerarquía de excepciones, facilitando su manejo en la capa REST mediante controladores de errores globales (ExceptionHandler).
 */
public abstract class BaseException extends RuntimeException {

  /**
   * Constructor con mensaje descriptivo.
   * @param message descripción de la causa de la excepción.
   */
  protected BaseException(String message) {
    super(message);
  }

  /**
   * Constructor con mensaje y causa original.
   * @param message descripción de la causa de la excepción.
   * @param cause excepción original que provocó el error.
   */
  protected BaseException(String message, Throwable cause) {
    super(message, cause);
  }
}
