package com.Perfumelandia.controller;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos") 
public class ProductoController {

    @Autowired
    private ProductoService service;
    private boolean datosCargados = false;

    @PostMapping
    public Producto registrarProducto(@RequestBody Producto p) {
        return service.saveProducto(p);
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
                if (!datosCargados && service.getProducto().isEmpty()) {
            service.saveProducto(new Producto(null, "Perfume Ocean", "Marca A", 10, 18990
              ));
            service.saveProducto(new Producto(null, "Perfume Sunset", "Marca B", 7, 22500
              ));
            service.saveProducto(new Producto(null, "Perfume Midnight", "Marca C", 5, 24990
              ));

            datosCargados = true;
        }
        return service.getProducto();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return service.getProductoId(id);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto p) {
        p.setId(id);
        return service.updateProducto(p);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        return service.deleteProducto(id);
    }
}