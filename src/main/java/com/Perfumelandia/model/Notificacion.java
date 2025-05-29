package com.Perfumelandia.model;

import jakarta.persistence.*;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String asunto;
    private String descripcion;

    public Notificacion() {}

    public Notificacion(String asunto, String descripcion) {
        this.asunto = asunto;
        this.descripcion = descripcion;
    }

    public String getAsunto() { return asunto; }
    public String getDescripcion() { return descripcion; }
}
