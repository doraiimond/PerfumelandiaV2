package com.Perfumelandia.assemblers;

import com.Perfumelandia.model.Producto;
import com.Perfumelandia.controllerV2.ProductoControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler <Producto,EntityModel<Producto>> {
    @Override

    public @NonNull EntityModel<Producto> toModel(@NonNull Producto producto){
        if (producto.getId() == null) {
            System.err.println("⚠️ Producto sin ID: " + producto);
            return EntityModel.of(producto); // Sin enlaces
        }

        return EntityModel.of(producto,
            linkTo(methodOn(ProductoControllerV2.class).obtenerProductoPorId(producto.getId())).withSelfRel(),
            linkTo(methodOn(ProductoControllerV2.class).eliminarProducto(producto.getId())).withRel("Eliminar"),
            linkTo(methodOn(ProductoControllerV2.class).obtenerProductos()).withRel("Productos"));
    }
}
