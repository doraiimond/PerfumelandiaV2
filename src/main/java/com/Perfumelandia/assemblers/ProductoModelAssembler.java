package com.Perfumelandia.assemblers;

import com.Perfumelandia.controllerV2.ProductoControllerV2;
import com.Perfumelandia.model.Producto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public @NonNull EntityModel<Producto> toModel(@NonNull Producto producto) {
        return EntityModel.of(producto,
            linkTo(methodOn(ProductoControllerV2.class).obtenerProductoPorId(producto.getId())).withSelfRel(),
            linkTo(methodOn(ProductoControllerV2.class).obtenerProductos()).withRel("productos"),
            linkTo(methodOn(ProductoControllerV2.class).registrarProducto(producto)).withRel("crear")
        );
    }
}
