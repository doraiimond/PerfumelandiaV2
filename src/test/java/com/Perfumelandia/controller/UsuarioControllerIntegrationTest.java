package com.Perfumelandia.controller;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
// Importar las clases necesarias para las pruebas de integración

import com.fasterxml.jackson.databind.ObjectMapper;// Importar ObjectMapper para convertir objetos a JSON

import org.junit.jupiter.api.Test;// Importar las anotaciones de prueba de JUnit
import org.springframework.beans.factory.annotation.Autowired;// Importar las anotaciones de Spring para inyección de dependencias
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; // Importar la anotación para pruebas de controladores web
import org.springframework.boot.test.mock.mockito.MockBean; // Importar la anotación para simular beans de Spring

import org.springframework.http.MediaType;// Importar el tipo de contenido MediaType para las peticiones HTTP
import org.springframework.test.web.servlet.MockMvc; // Importar MockMvc para realizar peticiones HTTP simuladas

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // Importar las clases necesarias para realizar peticiones HTTP simuladas
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;// Importar las clases necesarias para verificar los resultados de las peticiones HTTP simuladas
import static org.mockito.ArgumentMatchers.any; // Importar cualquier para simular argumentos en los métodos del servicio
import static org.mockito.Mockito.when; // Importar Mockito para simular el comportamiento de los métodos del servicio

@WebMvcTest(UsuarioController.class)// Anotar la clase para pruebas de controladores web, especificando el controlador a probar
public class UsuarioControllerIntegrationTest {
    // Inyectar MockMvc para realizar peticiones HTTP simuladas
    @Autowired
    private MockMvc mockMvc;
    // Simular el repositorio de usuarios
    @MockBean
    private UsuarioService usuarioService;
    // Usar ObjectMapper para convertir objetos a JSON
    @Autowired
    private ObjectMapper objectMapper;
    
    // Test para el caso de registro de un nuevo usuario
    @Test 
    void registrarUsuario_returnGuardado() throws Exception { // Crear un nuevo usuario para registrar
        Usuario nuevoUsuario = new Usuario(); // Crear una instancia de usuario
        nuevoUsuario.setNombre("Juan"); // Establecer el nombre del usuario
        nuevoUsuario.setEmail("juan@gmail.com"); // Establecer el email del usuario
        nuevoUsuario.setPassword("1234"); // Establecer la contraseña del usuario

        // Simular que el usuario ya existe
        when(usuarioService.registrar(any(Usuario.class))).thenReturn(nuevoUsuario); // Simular que el usuario ya está registrado


        // Realizar la petición POST para registrar un nuevo usuario
        mockMvc.perform(post("/api/v1/usuarios/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoUsuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@gmail.com"))
                .andExpect(jsonPath("$.password").value("1234"));
    }
    // Test para el caso de inicio de sesión con un usuario existente
    @Test
    void loginUsuario_returnOK() throws Exception {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNombre("Juan");
        usuarioExistente.setEmail("juan@gmail.com");
        usuarioExistente.setPassword("1234");

         // Simular que el usuario existe
        when(usuarioService.autenticar("juan@gmail.com", "1234"))
                .thenReturn(Optional.of(usuarioExistente));


        // Realizar la petición POST para iniciar sesión
        mockMvc.perform(post("/api/v1/usuarios/login") // Usar el endpoint de login
                .contentType(MediaType.APPLICATION_JSON) // Establecer el tipo de contenido a JSON
                .content(objectMapper.writeValueAsString(usuarioExistente))) // Convertir el usuario a JSON y enviarlo en el cuerpo de la petición
                .andExpect(status().isOk()) // Verificar que la respuesta tenga un estado 200 OK
                .andExpect(jsonPath("$.result").value("OK"))    // Verificar que el resultado sea "OK"
                .andExpect(jsonPath("$.nombre").value("Juan")) // Verificar que el nombre del usuario sea correcto
                .andExpect(jsonPath("$.email").value("juan@gmail.com")) // Verificar que el email del usuario sea correcto
                .andExpect(jsonPath("$.password").value("1234"));   // Verificar que la contraseña del usuario sea correcta
    }
    // Test para el caso de inicio de sesión con un usuario inexistente
    @Test
    void loginUsuario_returnError() throws Exception {
        Usuario usuarioInexistente = new Usuario();
        usuarioInexistente.setEmail("noexiste@gmail.com");
        usuarioInexistente.setPassword("1234");

        // Simular el comportamiento del repositorio para un usuario inexistente
        when(usuarioService.autenticar("noexiste@gmail.com", "1234")) // Intentar autenticar un usuario que no existe
                .thenReturn(Optional.empty());// Simular que el usuario no existe
        
                // Realizar la petición POST para iniciar sesión
        mockMvc.perform(post("/api/v1/usuarios/login") // Usar el endpoint de login
                .contentType(MediaType.APPLICATION_JSON) // Establecer el tipo de contenido a JSON
                .content(objectMapper.writeValueAsString(usuarioInexistente)))//
                .andExpect(status().isOk()) // Verificar que la respuesta tenga un estado 200 OK
                .andExpect(jsonPath("$.result").value("Error")); // Verificar que el resultado sea "Error"
    }
}