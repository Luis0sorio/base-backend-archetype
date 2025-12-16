package es.osorio.base.rest.mapper;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.rest.dto.request.CreateUsuarioDto;
import es.osorio.base.rest.dto.request.UpdateUsuarioDto;
import es.osorio.base.rest.dto.response.UsuarioResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * - Define las conversiones entre la entidad Usuario y los distintos DTOs.
 * - @Mapper(componentModel = "spring") MapStruct genera automáticamente una implementación y la registra como bean.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

  /**
   * Convierte una entidad Usuario a su DTO de respuesta.
   */
  UsuarioResponseDto toResponseDto(Usuario entity);

  /**
   * Convierte una lista de entidades Usuario a lista de DTOs de respuesta.
   */
  List<UsuarioResponseDto> toResponseDtoList(List<Usuario> entities);

  /**
   * Convierte un DTO de respuesta a entidad Usuario.
   */
  Usuario toEntity(UsuarioResponseDto dto);

  /**
   * Convierte un DTO de creación a entidad Usuario.
   */
  Usuario toEntity(CreateUsuarioDto dto);

  /**
   * Convierte un DTO de actualización a entidad Usuario.
   */
  Usuario toEntity(UpdateUsuarioDto dto);
}
