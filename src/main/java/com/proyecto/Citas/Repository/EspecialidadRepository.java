package com.proyecto.Citas.Repository;

import com.proyecto.Citas.Entidades.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
}