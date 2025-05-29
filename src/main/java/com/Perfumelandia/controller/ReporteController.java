package com.Perfumelandia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Perfumelandia.model.Reporte;
import com.Perfumelandia.service.ReporteService;


@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService serviceR;


    @PostMapping
    public Reporte enviarR(@RequestBody Reporte r) {
        return serviceR.guardarReporte(r);
    }
    
}