package com.Perfumelandia.controllerV2;

import com.Perfumelandia.model.Reporte;
import com.Perfumelandia.service.ReporteService;
import com.Perfumelandia.assemblers.ReporteModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/reportes")
@Tag(name = "Reportes", description = "Operaciones de los Reportes")
public class ReporteControllerV2 {

    @Autowired
    private ReporteService reporteServ;

    @Autowired
    private ReporteModelAssembler assembler;

    @Operation(summary = "Enviar Reportes",
               description = "Crea el reporte y lo guarda en la BD")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Reporte>> enviarR(@RequestBody Reporte r) {
        Reporte creado = reporteServ.guardarReporte(r);
        return ResponseEntity
            .created(linkTo(methodOn(ReporteControllerV2.class)
            .verReporte(creado.getId())).toUri())
            .body(assembler.toModel(creado));
    }

    @Operation(summary = "Ver Reporte",
               description = "Devuelve un reporte por su ID")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Reporte> verReporte(@PathVariable Long id) {
        Reporte reporte = reporteServ.obtenerReporte(id);
        return assembler.toModel(reporte);
    }
}
