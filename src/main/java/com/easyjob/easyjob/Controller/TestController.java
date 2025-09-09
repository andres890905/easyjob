package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final UsuarioRepository usuarioRepository;

    public TestController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/test-db")
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
