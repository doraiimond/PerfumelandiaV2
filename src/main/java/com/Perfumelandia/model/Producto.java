package com.Perfumelandia.model;


import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private long id;
    private String nombre;
    private int stock;
    private int precio;

    public static Optional<Producto> map(Object o){
    throw new UnsupportedOperationException("Unimplemented method 'map'");
    }

}