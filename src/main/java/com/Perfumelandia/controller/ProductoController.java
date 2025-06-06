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
    private ProductoService productoServ;
    private boolean datosCargados = false;

    @PostMapping
    public Producto registrarProducto(@RequestBody Producto p) {
        return productoServ.saveProducto(p);
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
                if (!datosCargados && productoServ.getProducto().isEmpty()) {
            productoServ.saveProducto(new Producto(null, "Perfume Ocean", "Marca A", 10, 18990
              ));
            productoServ.saveProducto(new Producto(null, "Perfume Sunset", "Marca B", 7, 22500
              ));
            productoServ.saveProducto(new Producto(null, "Perfume Midnight", "Marca C", 5, 24990
              ));

            datosCargados = true;
        }
        return productoServ.getProducto();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoServ.getProductoId(id);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto p) {
        p.setId(id);
        return productoServ.updateProducto(p);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        return productoServ.deleteProducto(id);
    }
}