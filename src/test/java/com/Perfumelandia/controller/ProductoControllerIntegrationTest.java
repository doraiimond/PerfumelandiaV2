package com.Perfumelandia.controller;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarProducto_returnProductoGuardado() throws Exception {
        Producto producto = new Producto(null, "Perfume Test", "Marca X", 10, 19990);
        Producto productoGuardado = new Producto(1L, "Perfume Test", "Marca X", 10, 19990);

        when(productoService.saveProducto(producto)).thenReturn(productoGuardado);

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Perfume Test"))
                .andExpect(jsonPath("$.marca").value("Marca X"))
                .andExpect(jsonPath("$.precio").value(19990));
    }

    @Test
    void obtenerProductos_returnListaProductos() throws Exception {
        List<Producto> productos = Arrays.asList(
                new Producto(1L, "Perfume A", "Marca A", 10, 18990),
                new Producto(2L, "Perfume B", "Marca B", 5, 22500)
        );

        when(productoService.getProducto()).thenReturn(productos);

        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Perfume A"));
    }

    @Test
    void obtenerProductoPorId_returnProducto() throws Exception {
        Producto producto = new Producto(1L, "Perfume Unico", "Marca Z", 3, 29900);
        when(productoService.getProductoId(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Perfume Unico"));
    }

    @Test
    void actualizarProducto_returnProductoActualizado() throws Exception {
        Producto productoActualizado = new Producto(1L, "Perfume Editado", "Marca E", 8, 20990);

        when(productoService.updateProducto(productoActualizado)).thenReturn(productoActualizado);

        mockMvc.perform(put("/api/v1/productos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Perfume Editado"))
                .andExpect(jsonPath("$.stock").value(8));
    }

    @Test
    void eliminarProducto_returnMensaje() throws Exception {
        when(productoService.deleteProducto(1L)).thenReturn("Producto eliminado");

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado"));
    }
}
