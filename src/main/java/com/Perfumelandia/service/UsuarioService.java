package com.example.bibliotecaduocV2.Service;

import com.example.bibliotecaduocV2.Model.usuario;
import com.example.bibliotecaduocV2.Repository.usuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioService {

    @Autowired //Inyectar el repositorio de usuario
    private usuarioRepository repo;
    //Método para registrar usuarios
    public usuario registrar(usuario u) {
        return repo.save(u);//Verificar si el usuario ya existe en la base de dato
    }
    //Método que autentifica los usuarios
    public Optional<usuario> autenticar(String email, String password) {
        return repo.findByEmail(email).filter(u -> u.getPassword().equals(password));
    }
}
