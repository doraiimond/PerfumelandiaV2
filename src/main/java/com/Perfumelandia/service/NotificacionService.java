package com.Perfumelandia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepository.findAll();
    }
}
