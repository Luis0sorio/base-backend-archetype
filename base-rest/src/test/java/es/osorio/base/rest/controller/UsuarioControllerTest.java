package es.osorio.base.rest.controller;

import es.osorio.base.core.domain.Usuario;
import es.osorio.base.core.dto.UsuarioFilterDto;
import es.osorio.base.core.service.UsuarioService;
import es.osorio.base.core.dto.response.UsuarioResponseDto;
//import es.osorio.base.rest.config.RestTestApplication;
import es.osorio.base.rest.RestTestApplication;
import es.osorio.base.rest.mapper.UsuarioMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioController.class)
@ContextConfiguration(classes = RestTestApplication.class)
class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  //@MockBean
  private UsuarioService usuarioService;

  @MockitoBean
 // @MockBean
  private UsuarioMapper usuarioMapper;

  @Test
  void shouldSearchUsuariosPaged() throws Exception {

    Usuario usuario = new Usuario("admin", "admin@test.com", "pwd");
    usuario.setId(1L);

    UsuarioResponseDto responseDto = new UsuarioResponseDto(
      1L,
      "admin",
      "admin@test.com",
      true,
      Set.of("ROL_USER")
    );

    Page<Usuario> page = new PageImpl<>(
      List.of(usuario),
      PageRequest.of(0, 10),
      1
    );

    when(usuarioService.search(any(UsuarioFilterDto.class), any(Pageable.class)))
      .thenReturn(page);

    when(usuarioMapper.toResponseDto(usuario))
      .thenReturn(responseDto);

    mockMvc.perform(get("/api/usuarios")
        .param("username", "adm")
        .param("page", "0")
        .param("size", "10"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.content[0].username").value("admin"))
      .andExpect(jsonPath("$.content[0].email").value("admin@test.com"))
      .andExpect(jsonPath("$.content.length()").value(1));
  }

  @Test
  void shouldFindByUsername() throws Exception {

    Usuario usuario = new Usuario("admin", "admin@test.com", "pwd");
    usuario.setId(1L);

    UsuarioResponseDto responseDto = new UsuarioResponseDto(
      1L,
      "admin",
      "admin@test.com",
      true,
      Set.of("ROL_USER")
    );

    when(usuarioService.findByUsername("admin"))
      .thenReturn(java.util.Optional.of(usuario));

    when(usuarioMapper.toResponseDto(usuario))
      .thenReturn(responseDto);

    mockMvc.perform(get("/api/usuarios/username/admin"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.username").value("admin"))
      .andExpect(jsonPath("$.email").value("admin@test.com"));
  }
}