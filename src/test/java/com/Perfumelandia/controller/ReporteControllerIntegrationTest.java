package com.Perfumelandia.controller;

import com.Perfumelandia.model.Reporte;
import com.Perfumelandia.service.ReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteController.class)
public class ReporteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReporteService reporteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void enviarReporte_returnReporteGuardado() throws Exception {
        Reporte reporte = new Reporte(null, "Eduardo", "eduardo@gmail.com", "Tengo una consulta");
        Reporte guardado = new Reporte(1L, "Eduardo", "eduardo@gmail.com", "Tengo una consulta");

        when(reporteService.guardarReporte(reporte)).thenReturn(guardado);

        mockMvc.perform(post("/api/v1/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Eduardo"))
                .andExpect(jsonPath("$.email").value("eduardo@gmail.com"))
                .andExpect(jsonPath("$.asunto").value("Tengo una consulta"));
    }
}
