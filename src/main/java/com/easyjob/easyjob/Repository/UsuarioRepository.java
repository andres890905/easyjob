package com.easyjob.easyjob.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easyjob.easyjob.Model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreo(String correo);

    Usuario findByCorreoAndContrasena(String correo, String contrasena);
}

