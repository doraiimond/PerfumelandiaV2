package com.Perfumelandia.controller;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario registrar(@RequestBody Usuario u) {
        return service.saveUsuario(u);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return service.getUsuarios();
    }
}