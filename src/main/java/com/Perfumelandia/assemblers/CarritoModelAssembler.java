package com.Perfumelandia.assemblers;

import com.Perfumelandia.controllerV2.CarritoControllerV2;
import com.Perfumelandia.model.Producto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public @NonNull EntityModel<Producto> toModel(@NonNull Producto producto) {
        return EntityModel.of(producto,
            linkTo(methodOn(CarritoControllerV2.class).buscarProducto(producto.getId())).withSelfRel(),
            linkTo(methodOn(CarritoControllerV2.class).verCarrito()).withRel("carrito"),
            linkTo(methodOn(CarritoControllerV2.class).eliminarProducto(producto.getId())).withRel("eliminar"),
            linkTo(methodOn(CarritoControllerV2.class).vaciarCarrito()).withRel("vaciar")
        );
    }
}
