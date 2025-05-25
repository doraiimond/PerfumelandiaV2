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
@RequestMapping("/api(v1/carrito)")
public class CarritoController {
    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoService;


    //Metodo para agregar libros al carrito
    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable int id) {
        Producto producto = productoService.getProductooId(id);
        if(producto != null){
            carrito.add(producto);
            return "Producto se agregado al carrito: " + producto.getNombre(); 
        }
        return "Libro no fue encontrado";
    }
    
    //metodo para ver el contenido del carrito
    @GetMapping
    public List<Producto> verCarrito() {
        return carrito;
    }
    
    //metodo para vaciar el carrito
    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "Carrito Vacio";
    }

    //metodo para eliminar un libro
    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id ){
        boolean eliminado = carrito.removeIf(libro -> libro.getId() == id);
        return eliminado ? "Perfume ha sido eliminado del carrito" : "Producto no estaba en el carrito";

    }

    //metodo para contar los items del carrito
    @GetMapping("/total")
    public int totalLibrosCarritos() {
        return carrito.size();
    }
    
}
