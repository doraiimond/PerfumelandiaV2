package com.Perfumelandia.repository;

import com.Perfumelandia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByEmail(long Id); // Para login más adelante si quieres
}