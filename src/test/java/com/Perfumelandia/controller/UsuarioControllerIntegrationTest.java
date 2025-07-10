package com.Perfumelandia.controller;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarUsuario_returnUsuarioRegistrado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@test.com");
        usuario.setPassword("12345");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre("Pedro");
        usuarioGuardado.setEmail("pedro@test.com");
        usuarioGuardado.setPassword("12345");

        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(usuarioGuardado);

        mockMvc.perform(post("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.email").value("pedro@test.com"));
    }

    @Test
    void loginUsuario_returnUsuarioExistente() throws Exception {
        Usuario datosLogin = new Usuario();
        datosLogin.setEmail("pedro@test.com");
        datosLogin.setPassword("12345");

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNombre("Pedro");
        usuarioExistente.setEmail("pedro@test.com");
        usuarioExistente.setPassword("12345");

        when(usuarioService.login("pedro@test.com", "12345")).thenReturn(usuarioExistente);

        mockMvc.perform(post("/api/v1/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datosLogin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.email").value("pedro@test.com"));
    }
}
