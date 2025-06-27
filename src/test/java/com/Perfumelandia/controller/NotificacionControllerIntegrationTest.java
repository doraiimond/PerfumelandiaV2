package com.Perfumelandia.controller;

import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.service.NotificacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionController.class)
public class NotificacionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NotificacionService notificacionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void agregarNotificacion_returnGuardada() throws Exception {
        Notificacion noti = new Notificacion("Promoción", "¡Gran descuento esta semana!");
        Notificacion guardada = new Notificacion("Promoción", "¡Gran descuento esta semana!");

        when(notificacionService.guardarNotificacion(noti)).thenReturn(guardada);

        mockMvc.perform(post("/api/v1/notificaciones/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noti)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.asunto").value("Promoción"))
                .andExpect(jsonPath("$.descripcion").value("¡Gran descuento esta semana!"));
    }

    @Test
    void obtenerNotificaciones_returnLista() throws Exception {
        List<Notificacion> lista = Arrays.asList(
                new Notificacion("Alerta", "Producto sin stock"),
                new Notificacion("Actualización", "Nuevo perfume disponible")
        );

        when(notificacionService.obtenerNotificaciones()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].asunto").value("Alerta"))
                .andExpect(jsonPath("$[1].descripcion").value("Nuevo perfume disponible"));
    }
}
