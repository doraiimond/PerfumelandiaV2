package com.Perfumelandia.controllerV2;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Perfumelandia.assemblers.CarritoModelAssembler;
import com.Perfumelandia.model.Producto;

import com.Perfumelandia.service.ProductoService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoControllerV2 {
    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoServ;
    private CarritoModelAssembler carritoAssembler;

    @PostMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id) {
        Producto producto = productoServ.getProductoId(id);
        if(producto != null){
            carrito.add(producto);
            return "Producto agregado al carrito: " + producto.getNombre(); 
        }
        return "producto no fue encontrado";
    }

    @DeleteMapping("/vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "Carrito Vacio";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id ){
        boolean eliminado = carrito.removeIf(producto -> producto.getId().equals((long) id));
        return eliminado ? "perfume ha sido eliminado del carrito" : "producto no estaba en el carrito";

    }
    //vercarrito
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> verCarrito() {
        List<EntityModel<Producto>> productos = carrito.stream()
            .map(carritoAssembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarritoControllerV2.class).verCarrito()).withSelfRel());
    }


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
