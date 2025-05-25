package com.Perfumelandia.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private long id;
    private String nombre;
    private String categoria;
    private String descripcion;
    private int stock;
    private int precio;
}