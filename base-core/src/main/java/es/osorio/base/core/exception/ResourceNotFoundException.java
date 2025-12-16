package es.osorio.base.core.exception;

/**
 * Excepción personalizada para indicar que un recurso no ha sido encontrado.
 * - Se utiliza cuando se intenta acceder a un recurso (ej. Usuario) que no existe en la base de datos.
 * - El mensaje se construye dinámicamente con el nombre del recurso y el identificador
 * - Facilita la depuración y la trazabilidad.
 */
public class ResourceNotFoundException extends BaseException {

  /**
   * Constructor que recibe el nombre del recurso y su identificador.
   * @param resource nombre del recurso (ej. "Usuario", "Rol").
   * @param id identificador del recurso buscado.
   */
  public ResourceNotFoundException(String resource, Object id) {
    super(resource + " no encontrado con identificador: " + id);
  }
}
