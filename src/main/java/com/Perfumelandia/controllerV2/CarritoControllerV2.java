package com.Perfumelandia.controllerV2;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.service.ProductoService;
import com.Perfumelandia.assemblers.CarritoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoControllerV2 {

    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CarritoModelAssembler assembler;


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> verCarrito() {
        List<EntityModel<Producto>> productos = carrito.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(productos,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarritoControllerV2.class).verCarrito()).withSelfRel());
    }


    @PostMapping("/agregar/{id}")
    public ResponseEntity<EntityModel<Producto>> agregarProducto(@PathVariable Long id) {
        Producto producto = productoService.getProductoId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        for (Producto p : carrito) {
            if (p.getId().equals(id)) {
                return ResponseEntity.badRequest().body(assembler.toModel(producto));
            }
        }
        carrito.add(producto);
        return ResponseEntity.created(
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarritoControllerV2.class)
                .buscarProducto(id)).toUri()
        ).body(assembler.toModel(producto));
    }

    @GetMapping("/{id}")
    public EntityModel<Producto> buscarProducto(@PathVariable Long id) {
        return carrito.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .map(assembler::toModel)
            .orElseThrow(() -> new NoSuchElementException("Producto no encontrado en el carrito con ID: " + id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = carrito.removeIf(p -> p.getId().equals(id));
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<?> vaciarCarrito() {
        carrito.clear();
        return ResponseEntity.noContent().build();
    }
}
