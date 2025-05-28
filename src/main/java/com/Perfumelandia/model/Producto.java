package com.Perfumelandia.model;

import java.util.Optional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    private String nombre;
    private String marca;
    private int stock;
    private int precio;

    

    public static Optional<Producto> map(Object o){
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }

}