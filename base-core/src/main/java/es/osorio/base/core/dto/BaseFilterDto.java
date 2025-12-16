package es.osorio.base.core.dto;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public abstract class BaseFilterDto {

  private Integer page = 0;
  private Integer size = 10;

  private String sortBy = "id";
  private Sort.Direction sortDirection = Sort.Direction.ASC;

}
