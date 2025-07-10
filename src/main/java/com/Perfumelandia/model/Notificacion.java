package com.Perfumelandia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String asunto;
    private String descripcion;


    public Notificacion(String asunto, String descripcion) {
        this.asunto = asunto;
        this.descripcion = descripcion;
    }
}
