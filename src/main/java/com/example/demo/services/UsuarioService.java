package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.errors.exception.ModelNotFoundException;
import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository persistir;

    public Object create(Usuario usuario) {
        return persistir.save(usuario);
    }

    public Object findAll() {
        return persistir.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> objeto = persistir.findById(id);
        return objeto.orElseThrow(() -> new ModelNotFoundException("Usuario não encontado") );
    }

    public void deleteById(Long id) {
        Optional<Usuario> objeto = persistir.findById(id);
        objeto.ifPresentOrElse((obj) -> {
            persistir.deleteById(obj.getId());
        }, () -> new ModelNotFoundException("Usuario não encontado"));
    }

    public Object update(Usuario usuario) {
        return persistir.save(usuario);
    }


}
