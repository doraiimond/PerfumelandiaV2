package com.Perfumelandia.assemblers;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.controllerV2.CarritoControllerV2;
import com.Perfumelandia.controllerV2.ProductoControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public @NonNull EntityModel<Producto> toModel(@NonNull Producto producto) {
        return EntityModel.of(producto,
            linkTo(methodOn(ProductoControllerV2.class)
                .obtenerProductoPorId(producto.getId())).withRel("VerDetalle"),
            linkTo(methodOn(CarritoControllerV2.class)
                .eliminarProducto(producto.getId())).withRel("EliminarDelCarrito"),
            linkTo(methodOn(CarritoControllerV2.class)
                .verCarrito()).withRel("VerCarrito"),
            linkTo(methodOn(CarritoControllerV2.class)
                .confirmarCompra()).withRel("ConfirmarCompra")
        );
    }
}
