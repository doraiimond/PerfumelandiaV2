package com.Perfumelandia.assemblers;

import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.controller.NotificacionController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class NotificacionModelAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {

    @Override
    public @NonNull EntityModel<Notificacion> toModel(@NonNull Notificacion notificacion) {
        return EntityModel.of(notificacion,
            linkTo(methodOn(NotificacionController.class)
                .obtenerNotificaciones()).withRel("TodasLasNotificaciones"));
    }
}
