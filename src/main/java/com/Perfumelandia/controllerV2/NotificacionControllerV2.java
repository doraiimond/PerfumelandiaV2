package com.Perfumelandia.controllerV2;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.service.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/v2/notificaciones")
@Tag(name="Notificaciones",
    description="Operaciones de las Notificaciones")
public class NotificacionControllerV2 {

    @Autowired
    private NotificacionService notificacionServ;

    @Operation(summary="Agreagar Notificacion",
                description="Crea una notificaciones y la guarda en la BD")
    @PostMapping("/agregar")
    public Notificacion agregarNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionServ.guardarNotificacion(notificacion);
    }

    @Operation(summary="Listar Notificaciones",
                description="Muestra las notificaciones Registradas")    
    @GetMapping
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionServ.obtenerNotificaciones();
    }
}
