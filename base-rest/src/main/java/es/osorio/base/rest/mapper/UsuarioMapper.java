package es.osorio.base.rest.mapper;

import es.osorio.base.core.domain.Rol;
import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.request.CreateUsuarioDto;
import es.osorio.base.core.dto.request.UpdateUsuarioDto;
import es.osorio.base.core.dto.response.UsuarioResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
   * Convierte un DTO de creación a entidad Usuario.
   */
  Usuario toEntity(CreateUsuarioDto dto);

  /**
   * Convierte un DTO de actualización a entidad Usuario.
   */
  void updateEntityFromDto(UpdateUsuarioDto dto, @MappingTarget Usuario entity);

  default Set<String> mapRoles(Set<Rol> roles) {
    return roles.stream()
      .map(r -> r.getTipo().name())
      .collect(Collectors.toSet());
  }
}
