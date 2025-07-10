package com.Perfumelandia.assemblers;

import com.Perfumelandia.controllerV2.NotificacionControllerV2;
import com.Perfumelandia.model.Notificacion;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class NotificacionModelAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {

    @Override
    public @NonNull EntityModel<Notificacion> toModel(@NonNull Notificacion notificacion) {
        return EntityModel.of(notificacion,
            linkTo(methodOn(NotificacionControllerV2.class).listarNotificaciones()).withRel("notificaciones"),
            linkTo(methodOn(NotificacionControllerV2.class).crearNotificacion(notificacion)).withRel("crear")
        );
    }
}
