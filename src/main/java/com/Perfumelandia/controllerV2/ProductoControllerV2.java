package com.Perfumelandia.controllerV2;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.service.ProductoService;
import com.Perfumelandia.assemblers.ProductoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;


import java.util.stream.Collectors;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/productos") 
@Tag(name="Productos",
    description="Operaciones de los productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoServ;
    private boolean datosCargados = false;

    @Autowired
    private ProductoModelAssembler assembler;

    @Operation(summary="Agregar un producto al carro",
                description="Agrega productos segun su id")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> registrarProducto(@RequestBody Producto p) {
        Producto crear = productoServ.saveProducto(p);
        return ResponseEntity
            .created(linkTo(methodOn(ProductoControllerV2.class).
            obtenerProductoPorId(crear.getId())).toUri())
            .body(assembler.toModel(crear));
    }

    @Operation(summary="Listar Productos",
                description="Muestra los productos Registrados en la base de datos")    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> obtenerProductos() {
                if (!datosCargados && productoServ.getProducto().isEmpty()) {
            productoServ.saveProducto(new Producto(null, "Perfume Ocean", "Marca A", 10, 18990
              ));
            productoServ.saveProducto(new Producto(null, "Perfume Sunset", "Marca B", 7, 22500
              ));
            productoServ.saveProducto(new Producto(null, "Perfume Midnight", "Marca C", 5, 24990
              ));

            datosCargados = true;
        }
        List<EntityModel<Producto>> productos = productoServ.getProducto().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos,
            linkTo(methodOn(ProductoControllerV2.class).
            obtenerProductos()).withSelfRel());
    }

    @Operation(summary="Obtener Producto",
                description="Obtiene un producto segun su id")
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoServ.getProductoId(id);
    }

    @Operation(summary="actualizar Producto",
                description="Actualiza un producto segun su id")
    @PutMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto p) {
        p.setId(id);
        return productoServ.updateProducto(p);
    }

    @Operation(hidden = true)
    @GetMapping("/{id}/actualizar")
    public ResponseEntity<Void> getActualizarProductoLink(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }




    @Operation(summary="Eliminar Producto",
                description="Elimina un producto segun su id")    
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        return productoServ.deleteProducto(id);
    }
}