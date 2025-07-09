package com.Perfumelandia.service;


import com.Perfumelandia.model.Producto;
import com.Perfumelandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    public Producto saveProducto(Producto p) {
        return productoRepo.save(p);
    }

    public List<Producto> getProducto() {
        return productoRepo.findAll();
    }

    public Producto getProductoId(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    public Producto updateProducto(Producto p) {
        return productoRepo.save(p);
    }

    public String deleteProducto(Long id) {
        productoRepo.deleteById(id);
        return "Producto eliminado";
    }

    public int totalProducto() {
        return productoRepo.findAll().size();
    }
}