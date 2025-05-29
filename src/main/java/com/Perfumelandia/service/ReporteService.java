package com.Perfumelandia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Perfumelandia.model.Reporte;
import com.Perfumelandia.repository.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteR;


    public Reporte guardarReporte(Reporte r) {
        return reporteR.save(r);
    }

}