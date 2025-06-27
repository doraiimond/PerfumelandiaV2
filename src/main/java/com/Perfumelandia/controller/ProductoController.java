package com.Perfumelandia.controller;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/productos") 
@Tag(name="Productos",
    description="Operaciones de los productos")
public class ProductoController {

    @Autowired
    private ProductoService productoServ;
    private boolean datosCargados = false;

    @Operation(summary="Agregar un producto al carro",
                description="Agrega productos segun su id")
    @PostMapping
    public Producto registrarProducto(@RequestBody Producto p) {
        return productoServ.saveProducto(p);
    }

    @Operation(summary="Listar Productos",
                description="Muestra los productos Registrados en la base de datos")    
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

    @Operation(summary="Obtener Producto",
                description="Obtiene un producto segun su id")
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoServ.getProductoId(id);
    }

    @Operation(summary="actualizar Producto",
                description="Actualiza un producto segun su id")
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto p) {
        p.setId(id);
        return productoServ.updateProducto(p);
    }

    @Operation(summary="Eliminar Producto",
                description="Elimina un producto segun su id")    
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        return productoServ.deleteProducto(id);
    }
}