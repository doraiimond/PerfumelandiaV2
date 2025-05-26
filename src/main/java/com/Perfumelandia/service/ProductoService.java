package com.Perfumelandia.service;


import com.Perfumelandia.model.Producto;
import com.Perfumelandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public Producto saveProducto(Producto p) {
        return repo.save(p);
    }

    public List<Producto> getProducto() {
        return repo.findAll();
    }

    public Producto getProductoId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Producto updateProducto(Producto p) {
        return repo.save(p);
    }

    public String deleteProducto(Long id) {
        repo.deleteById(id);
        return "Producto eliminado";
    }

    public int totalProducto() {
        return repo.findAll().size();
    }
}