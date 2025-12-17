package es.osorio.base.core.dto.request;

public record UsuarioSearchRequestDto(
  String username,
  Boolean activo,
  Integer page,
  Integer size,
  String sort
) {}
