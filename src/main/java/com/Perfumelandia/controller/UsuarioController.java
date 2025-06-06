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
    private UsuarioService usuarioServ;

    @PostMapping
    public Usuario registrar(@RequestBody Usuario u) {
        return usuarioServ.saveUsuario(u);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioServ.getUsuarios();
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario datos) {
        return usuarioServ.login(datos.getEmail(), datos.getPassword());
    }

}