package com.Perfumelandia.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Perfumelandia.model.Producto;

import com.Perfumelandia.service.ProductoService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {
    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoService;

    
    @PostMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id) {

        Producto producto = productoService.getProductoId(id);
        if(producto != null){
            carrito.add(producto);
            return "Producto agregado al carrito: " + producto.getNombre(); 
        }
        return "producto no fue encontrado";
    }

    @GetMapping
    public List<Producto> verCarrito() {
        return carrito;
    }
    
    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "Carrito Vacio";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id ){
        boolean eliminado = carrito.removeIf(libro -> libro.getId() == id);
        return eliminado ? "Perfume ha sido eliminado del carrito" : "Producto no estaba en el carrito";

    }

    @GetMapping("/total")
    public int totalProductosCarritos() {
        return carrito.size();
    }
    //libro
    @PostMapping("/confirmar")
    public String confirmarCompra() {
        Map<Long, Integer> cantidades = new HashMap<>();

        for (Producto producto : carrito) {
            long id = producto.getId();
            cantidades.put(id, cantidades.getOrDefault(id, 0) + 1);
        }
        for (Map.Entry<Long, Integer> entry : cantidades.entrySet()) {
            Long productoId = entry.getKey();
            int cantidadComprada = entry.getValue();
            Producto productoEnBD = productoService.getProductoId(productoId);

            if (productoEnBD != null && productoEnBD.getStock() >= cantidadComprada) {
                productoEnBD.setStock(productoEnBD.getStock() - cantidadComprada);
                productoService.saveProducto(productoEnBD);
            } else {
                return "No hay stock" + productoId;
            }
        }

        carrito.clear();
        return "Compra confirmada. ¡Gracias por tu compra!";
    }

}
