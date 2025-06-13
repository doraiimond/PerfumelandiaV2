package com.Perfumelandia.model;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.Data;

@Entity //Indicar que la clase usuario es un objeto JPA
@Data
public class Usuario { //Está clase se transformará en una tabla de nuestra base de dato MySQL
    @Id //Generar el campo clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generar el id automatico
    private Long id;

    private String nombre;
    private String email;
    private String password;

    public static Optional<Usuario> map(Object o) { //Método automatico para enviar el objeto usuario a la base de datos
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}
