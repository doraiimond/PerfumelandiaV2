package com.Perfumelandia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Perfumelandia.model.Reporte;
import com.Perfumelandia.service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name="Reportes",
    description="Operaciones de los Reportes") 
public class ReporteController {

    @Autowired
    private ReporteService reporteServ;

    @Operation(summary="Enviar Reportes",
                description="Crea el reporte y lo guarda en la BD")
    @PostMapping
    public Reporte enviarR(@RequestBody Reporte r) {
        return reporteServ.guardarReporte(r);
    }

    @Operation(summary = "Ver Reporte",
       description = "Devuelve un reporte por su ID")
    @GetMapping("/{id}")
    public Reporte verReporte(@PathVariable Long id) {
        return reporteServ.obtenerReporte(id);
    }

    
}