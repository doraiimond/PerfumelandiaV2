package com.Perfumelandia.controller;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.service.ProductoService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarritoController.class)
public class CarritoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;


    @Test
    void agregarProductoAlCarrito_returnOK() throws Exception {
        Producto producto = new Producto(1L, "Perfume Test", "Marca X", 5, 19990);

        when(productoService.getProductoId(1L)).thenReturn(producto);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto agregado al carrito: Perfume Test"));
    }

    @Test
    void agregarProductoInexistente_returnError() throws Exception {
        when(productoService.getProductoId(99L)).thenReturn(null);

        mockMvc.perform(post("/api/v1/carrito/agregar/99"))
                .andExpect(status().isOk())
                .andExpect(content().string("producto no fue encontrado"));
    }

    @Test
    void verCarritoInicial_returnVacio() throws Exception {
        mockMvc.perform(get("/api/v1/carrito"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void vaciarCarrito_returnMensaje() throws Exception {
        mockMvc.perform(delete("/api/v1/carrito/vaciar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrito Vacio"));
    }

    @Test
    void confirmarCompra_sinStock_returnError() throws Exception {
        Producto producto = new Producto(1L, "Perfume Test", "Marca X", 0, 19990);
        when(productoService.getProductoId(1L)).thenReturn(producto);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"));
        mockMvc.perform(post("/api/v1/carrito/confirmar"))
                .andExpect(status().isOk())
                .andExpect(content().string("No hay stock1"));
    }
}
