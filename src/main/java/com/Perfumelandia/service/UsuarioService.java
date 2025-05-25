package com.Perfumelandia.service;

import com.Perfumelandia.model.Usuario;
import com.Perfumelandia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario saveUsuario(Usuario u) {
        return repo.save(u);
    }

    public List<Usuario> getUsuarios() {
        return repo.findAll();
    }

    public Usuario getUsuarioId(int id) {
        return repo.findById(id).orElse(null);
    }

    public Usuario updateUsuario(Usuario u) {
        return repo.save(u);
    }

    public String deleteUsuario(int id) {
        repo.deleteById(id);
        return "Usuario eliminado";
    }

    public int totalUsuarios() {
        return repo.findAll().size();
    }

    public Usuario login(String email, String password) {
        return repo.inicioS(email, password);
    }    
}
