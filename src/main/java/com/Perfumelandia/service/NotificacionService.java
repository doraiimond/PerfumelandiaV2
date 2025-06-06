package com.Perfumelandia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Perfumelandia.model.Notificacion;
import com.Perfumelandia.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepo;

    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepo.save(notificacion);
    }

    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepo.findAll();
    }
}
