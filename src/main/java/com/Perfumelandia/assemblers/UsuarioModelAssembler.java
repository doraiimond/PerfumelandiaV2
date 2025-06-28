package com.Perfumelandia.assemblers;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.controllerV2.UsuarioControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler <Usuario,EntityModel<Usuario>> {
    @Override
    public @NonNull EntityModel<Usuario> toModel (@NonNull Usuario usuario){
        return EntityModel.of(usuario,
        linkTo(methodOn(UsuarioControllerV2.class).
        registrar(null)).withSelfRel(),
        linkTo(methodOn(UsuarioControllerV2.class).
        login(usuario)).withRel("Login"));
    }
}   
