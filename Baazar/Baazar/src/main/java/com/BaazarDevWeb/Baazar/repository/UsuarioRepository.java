package com.BaazarDevWeb.Baazar.repository;

import com.BaazarDevWeb.Baazar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
}
