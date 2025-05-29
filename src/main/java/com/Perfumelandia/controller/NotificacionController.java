package com.Perfumelandia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.service.NotificacionService;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/agregar")
    public Notificacion agregarNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.guardarNotificacion(notificacion);
    }

    @GetMapping
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionService.obtenerNotificaciones();
    }
}
