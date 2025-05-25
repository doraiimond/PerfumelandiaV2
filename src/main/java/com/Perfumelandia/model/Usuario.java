package com.Perfumelandia.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    private String nombre;
    private String email;
    private String password;

    public static Optional<Usuario> map(Object o){
    throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}