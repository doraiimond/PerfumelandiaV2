package com.Perfumelandia.controllerV2;

import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.service.NotificacionService;
import com.Perfumelandia.assemblers.NotificacionModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/notificaciones")

public class NotificacionControllerV2 {

    @Autowired
    private NotificacionService notificacionServ;

    @Autowired
    private NotificacionModelAssembler assembler;

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Notificacion>> crearNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion creada = notificacionServ.guardarNotificacion(notificacion);
        return ResponseEntity
            .created(linkTo(methodOn(NotificacionControllerV2.class)
            .listarNotificaciones()).toUri())
            .body(assembler.toModel(creada));
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Notificacion>> listarNotificaciones() {
        List<EntityModel<Notificacion>> notificaciones = notificacionServ.obtenerNotificaciones().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(notificaciones,
            linkTo(methodOn(NotificacionControllerV2.class).listarNotificaciones()).withSelfRel());
    }
}
