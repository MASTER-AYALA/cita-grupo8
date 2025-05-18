package com.proyecto.Citas.Service;


import com.proyecto.Citas.Entidades.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.Citas.Repository.ConsultaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> obtenerConsultaPorId(Integer id) {
        return consultaRepository.findById(id);
    }

    public Consulta guardarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void eliminarConsulta(Integer id) {
        consultaRepository.deleteById(id);
    }
}