package es.osorio.base.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFilterDto extends BaseFilterDto {

  private String username;
  private String email;
  private Boolean activo;

}
