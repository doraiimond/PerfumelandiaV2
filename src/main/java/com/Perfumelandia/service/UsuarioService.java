package com.Perfumelandia.service;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Usuario saveUsuario(Usuario u) {
        return usuarioRepo.save(u);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario getUsuarioId(int id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Usuario u) {
        return usuarioRepo.save(u);
    }

    public String deleteUsuario(int id) {
        usuarioRepo.deleteById(id);
        return "Usuario eliminado";
    }

    public int totalUsuarios() {
        return usuarioRepo.findAll().size();
    }

    public Usuario login(String email, String password) {
        return usuarioRepo.findByEmailAndPassword(email, password);
    }    
}
