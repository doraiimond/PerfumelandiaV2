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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/carrito")
@Tag(name="Carrito de Compras",
    description="Operaciones del carrito")
public class CarritoController {
    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoServ;
    
    @Operation(summary="Agregar un producto al carro",
                description="Agrega productos segun su id")
    @PostMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id) {

        Producto producto = productoServ.getProductoId(id);
        if(producto != null){
            carrito.add(producto);
            return "Producto agregado al carrito: " + producto.getNombre(); 
        }
        return "producto no fue encontrado";
    }

    @Operation(summary="Vacioar Carrito",
                description="Elimina los productos agregados al carro")
    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "Carrito Vacio";
    }

    @Operation(summary="Eliminar Producto",
                description="Elimina los productos agregados al carro individualmente")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id ){
        boolean eliminado = carrito.removeIf(producto -> producto.getId().equals((long) id));
        return eliminado ? "perfume ha sido eliminado del carrito" : "producto no estaba en el carrito";

    }

    @Operation(summary="Lisar Carrito",
                description="Muestra los Productos que se añadieron al carrito")    
    @GetMapping
    public List<Producto> verCarrito() {
        return carrito;
    }

    @Operation(summary="Confirmar Compra",
                description="Confirma la compra y nos resta Stock")
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
            Producto productoEnBD = productoServ.getProductoId(productoId);

            if (productoEnBD != null && productoEnBD.getStock() >= cantidadComprada) {
                productoEnBD.setStock(productoEnBD.getStock() - cantidadComprada);
                productoServ.updateProducto(productoEnBD);
            } else {
                return "No hay stock" + productoId;
            }
        }

        carrito.clear();
        return "Gracias por tu compra";
    }

}
