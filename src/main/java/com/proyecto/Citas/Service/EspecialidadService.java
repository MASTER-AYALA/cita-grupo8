package com.proyecto.Citas.Service;

import com.proyecto.Citas.Entidades.Especialidad;
import com.proyecto.Citas.Repository.EspecialidadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> obtenerEspecialidadPorId(Integer id) {
        return especialidadRepository.findById(id);
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public void eliminarEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }
}