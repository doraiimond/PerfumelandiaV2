package com.Perfumelandia.controller;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioControllerIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @PostMapping("/login")
    public Map<String, Object> loginUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> encontrado = usuarioService.autenticar(
            usuario.getEmail(),
            usuario.getPassword()
        );

        if (encontrado.isPresent()) {
            Usuario u = encontrado.get();
            return Map.of(
                "result", "OK",
                "nombre", u.getNombre(),
                "email", u.getEmail(),
                "password", u.getPassword() // Solo si no hay riesgo de exposición ⚠️
            );
        } else {
            return Map.of("result", "Error");
        }
    }
}
