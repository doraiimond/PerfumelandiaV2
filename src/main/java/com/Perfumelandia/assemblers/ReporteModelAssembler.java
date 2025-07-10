package com.Perfumelandia.assemblers;

import com.Perfumelandia.controllerV2.ReporteControllerV2;
import com.Perfumelandia.model.Reporte;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<Reporte, EntityModel<Reporte>> {

    @Override
    public @NonNull EntityModel<Reporte> toModel(@NonNull Reporte reporte) {
        return EntityModel.of(reporte,
            linkTo(methodOn(ReporteControllerV2.class).verReporte(reporte.getId())).withSelfRel(),
            linkTo(methodOn(ReporteControllerV2.class).enviarR(reporte)).withRel("crear")
        );
    }
}
