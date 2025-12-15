package es.osorio.base.core.repository;

import es.osorio.base.core.domain.Rol;
import es.osorio.base.core.domain.RolTipo;
import es.osorio.base.core.repository.base.BaseRepository;

import java.util.Optional;

public interface RolRepository extends BaseRepository<Rol, Long> {

  Optional<Rol> findByTipo(RolTipo tipo); // Devuelve el rol si existe.
}
