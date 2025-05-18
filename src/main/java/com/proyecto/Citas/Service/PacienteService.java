package com.proyecto.Citas.Service;



import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.Citas.Repository.PacienteRepository;
import com.proyecto.Citas.Entidades.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPacientePorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
    }
}