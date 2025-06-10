package com.Perfumelandia.controller;

//impotar las clases del backed necesarias para las pruebas de integracion
import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
//importar clase ObjectMapper para
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//
import org.springframework.http.MediaType;
//
import org.springframework.test.web.servlet.MockMvc;
//importar las clases necesarias para realizar las peticiones HTTP simuladas
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//importar clases necesarias para verificar los resultados de las peticiones HTTP simuladas
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//importar any para simular los argumentos en los metodos del servicio de usuario
import static org.mockito.ArgumentMatchers.any;
//impotar Mockito para simular el comportamiento de los metodos del servicio Usuario con when
import static org.mockito.Mockito.when;

import java.util.Optional;

//usar la anotacion WebMvcTest para crear una prueba a un controlador especifico (UsuarioController
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerIntegrationTest {
    //inyectar MockMvs para realizar las peticiones HTTP simuladas
    @Autowired
    private MockMvc mockMvc;

    //simular el servicio de usuario
    @MockBean
    private UsuarioService UsuarioService;

    //usar ObjectMapper para convertir los objetos a JSON
    @Autowired
    private ObjectMapper objectMapper;

    //test para simular el registro de un nuevo usuario
    @Test
    void registraUsuario_ReturnGuardar() throws Exception {
        Usuario newUser = new Usuario(); //crear una instancia de usuario
        newUser.setNombre("Juan"); //establecer el nombre del usuario de simulacion
        newUser.setEmail("Juan@gmail.com"); //establecer el gmail del usuario de simulacion
        newUser.setPassword("hola"); //establecer el contraseña del usuario de simulacion

        //simular el comportamiento del metodo registar de usuarioService
        when(UsuarioService.saveUsuario(any(Usuario.class)))
            .thenReturn(newUser);
        
        //simular la peticion POST de usuarioController para registar
        mockMvc.perform(post("/api/v1/usuarios/registrar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newUser)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Juan"))
            .andExpect(jsonPath("$.email").value("Juan@gmail.com"))
            .andExpect(jsonPath("$.Password").value("hola"));
    }

    //Test para simular inicio de sesion de un usuario registrado
    @Test
    void loginUsuario_ReturnOk() throws Exception {
        Usuario userExistente = new Usuario();
        userExistente.setNombre("Juan"); 
        userExistente.setEmail("Juan@gmail.com");
        userExistente.setPassword("hola");

        //simular el comportamiento del metodo autenticar del servicio de UsuarioService con un usuario registrado
        when(UsuarioService.login("Juan@gmail.com", "hola"))
            .thenReturn(Optional.of(userExistente));


        mockMvc.perform(post("/api/v1/usuarios/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper.writeValueAsString(userExistente)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value())
    }

    //test login usuario de registrado
    @Test
    void loginUsuario_ReturnError() throws Exception {
        Usuario userInexistente = new Usuario();
        userInexistente.setEmail("hola@gmail.com");
        userInexistente.setPassword("hola");

        //simular con usuario no registrado
        when(UsuarioService.login("hola@gmail.com", "hola"))
        .thenReturn(Optional.empty());


        //simular la peticion post del usuariocontroller para inciar sesion con un usuario no registrado

        mockMvc.perform(post("/api/v1/usuarios/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userInexistente)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$result","error"));
    }
}
