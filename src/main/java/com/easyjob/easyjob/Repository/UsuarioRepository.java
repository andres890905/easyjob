package com.easyjob.easyjob.Repository;

import com.easyjob.easyjob.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
