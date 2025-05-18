package com.proyecto.Citas.Service;

import com.proyecto.Citas.Entidades.Cita;
import com.proyecto.Citas.Repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> obtenerTodasLasCitas() { // Cambiado a obtenerTodasLasCitas()
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerCitaPorId(Integer id) {
        return citaRepository.findById(id);
    }

    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public void eliminarCita(Integer id) {
        citaRepository.deleteById(id);
    }
}