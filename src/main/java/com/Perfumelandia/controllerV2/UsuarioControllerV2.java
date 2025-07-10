package com.Perfumelandia.controllerV2;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
import com.Perfumelandia.assemblers.UsuarioModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin

public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioModelAssembler assembler;

    @PostMapping(value = "/registrar", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> registrar(@RequestBody Usuario u) {
        Usuario registrado = service.saveUsuario(u);
        return ResponseEntity
            .created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).registrar(registrado)).toUri())
            .body(assembler.toModel(registrado));
    }

    @PostMapping(value = "/login", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Map<String, String>>> login(@RequestBody Usuario datos) {
        Usuario user = service.login(datos.getEmail(), datos.getPassword());
        Map<String, String> respuesta = new HashMap<>();

        if (user != null) {
            respuesta.put("result", "OK");
            respuesta.put("nombre", user.getNombre());
            respuesta.put("email", user.getEmail());
            // No enviar contraseña por seguridad
        } else {
            respuesta.put("result", "Error");
        }

        EntityModel<Map<String, String>> model = EntityModel.of(respuesta);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).login(datos)).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).registrar(new Usuario())).withRel("registrar"));

        return ResponseEntity.ok(model);
    }
}
