package es.osorio.base.rest.controller;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.dto.request.UpdateUsuarioDto;
import es.osorio.base.core.exception.ResourceNotFoundException;
import es.osorio.base.core.service.UsuarioService;
import es.osorio.base.rest.controller.base.BaseController;
import es.osorio.base.core.dto.request.CreateUsuarioDto;
import es.osorio.base.core.dto.response.UsuarioResponseDto;
import es.osorio.base.rest.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends BaseController {

  private final UsuarioService usuarioService;
  private final UsuarioMapper usuarioMapper;

  public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
    this.usuarioService = usuarioService;
    this.usuarioMapper = usuarioMapper;
  }

  @PostMapping
  public ResponseEntity<UsuarioResponseDto> create (@Valid @RequestBody CreateUsuarioDto dto) {
    Usuario usuario = usuarioService.create(dto);
    return created(usuarioMapper.toResponseDto(usuario));
  }

  @GetMapping("/usuarios/{id}")
  public ResponseEntity<UsuarioResponseDto> findById (@PathVariable Long id) {
    Usuario usuario = usuarioService.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Nombre de usuario:", id)
      );

    return ok(usuarioMapper.toResponseDto(usuario));
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<UsuarioResponseDto> findByUsername (@Valid @PathVariable String username) {
    Usuario usuario = usuarioService.findByUsername(username)
      .orElseThrow(() ->
        new ResourceNotFoundException("Nombre de usuario:", username)
      );

    return ok(usuarioMapper.toResponseDto(usuario));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuarioResponseDto> update(
    @PathVariable Long id,
    @Valid @RequestBody UpdateUsuarioDto dto) {

    Usuario usuario = usuarioService.update(id, dto);
    return ok(usuarioMapper.toResponseDto(usuario));
  }

  @GetMapping
  public ResponseEntity<Page<UsuarioResponseDto>> search(UsuarioFilterDto filter, Pageable pageable) {
    Page<Usuario> page = usuarioService.search(filter, pageable);
    return ok(page.map(usuarioMapper::toResponseDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    usuarioService.delete(id);
    return noContent();
  }
}
