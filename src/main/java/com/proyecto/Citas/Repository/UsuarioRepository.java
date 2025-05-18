package com.proyecto.Citas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.Citas.Entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}