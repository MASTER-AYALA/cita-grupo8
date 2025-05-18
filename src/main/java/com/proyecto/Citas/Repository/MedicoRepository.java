package com.proyecto.Citas.Repository;

import org.springframework.stereotype.Repository;

import com.proyecto.Citas.Entidades.Medico;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
