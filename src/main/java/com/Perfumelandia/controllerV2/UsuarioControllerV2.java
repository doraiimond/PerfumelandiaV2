package com.Perfumelandia.controllerV2;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/v2/usuarios") 
@CrossOrigin
@Tag(name="Usuarios",
    description="Operaciones de los Usuarios") 
public class UsuarioControllerV2 {
    @Autowired
    private UsuarioService service;

    @Operation(summary="Registar Usuario",
                description="Registra un usuario en la abse de datos")
    @PostMapping
    public Usuario registrar(@RequestBody Usuario u) {
        return service.saveUsuario(u);
    }

    @Operation(summary="Validar Usuario",
                description="Valida que el usuario exista en la bd")
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario datos) {
        return service.login(datos.getEmail(), datos.getPassword());
    }

}