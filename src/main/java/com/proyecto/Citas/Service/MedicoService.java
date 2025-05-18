package com.proyecto.Citas.Service;

import com.proyecto.Citas.Entidades.Medico;
import com.proyecto.Citas.Repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obtenerMedicoPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void eliminarMedico(Integer id) {
        medicoRepository.deleteById(id);
    }
}