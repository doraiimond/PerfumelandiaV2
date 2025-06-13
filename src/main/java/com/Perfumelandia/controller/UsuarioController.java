package com.Perfumelandia.controller;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/v1/usuarios") //URL base para las peticiones HTTP 
@CrossOrigin //Permitir peticiones desde cualquier origen
public class UsuarioController {
    @Autowired
    private UsuarioService serv;

    @PostMapping("/registrar") //Método para registrar un usuario en la tabla usuario
    public Usuario registrar(@RequestBody Usuario u) {
        return serv.registrar(u);
    }

    //Método para autenticar usuario
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario u) {
        Optional<Usuario> user = serv.autenticar(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
        } else {
            response.put("result", "Error");
        }
        return response;
    }

}