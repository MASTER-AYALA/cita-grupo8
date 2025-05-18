package com.proyecto.Citas.Controller;


import com.proyecto.Citas.dto.Consultadto;
import com.proyecto.Citas.Entidades.Consulta;
import com.proyecto.Citas.Service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consultadto>> listarConsultas() {
        List<Consulta> consultas = consultaService.listarConsultas();
        List<Consultadto> consultaDtos = consultas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(consultaDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultadto> obtenerConsultaPorId(@PathVariable Integer id) {
        Optional<Consulta> consulta = consultaService.obtenerConsultaPorId(id);
        if (consulta.isPresent()) {
            return new ResponseEntity<>(convertToDto(consulta.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Consultadto> guardarConsulta(@RequestBody Consultadto consultaDto) {
        Consulta consulta = convertToEntity(consultaDto);
        Consulta nuevaConsulta = consultaService.guardarConsulta(consulta);
        return new ResponseEntity<>(convertToDto(nuevaConsulta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultadto> actualizarConsulta(@PathVariable Integer id, @RequestBody Consultadto consultaDto) {
        Optional<Consulta> consultaExistente = consultaService.obtenerConsultaPorId(id);
        if (consultaExistente.isPresent()) {
            Consulta consulta = convertToEntity(consultaDto);
            // Elimina esta línea: consulta.setIdConsulta(id);
            Consulta consultaActualizadaGuardada = consultaService.guardarConsulta(consulta);
            return new ResponseEntity<>(convertToDto(consultaActualizadaGuardada), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Integer id) {
        consultaService.eliminarConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Consultadto convertToDto(Consulta consulta) {
        Consultadto dto = new Consultadto();
        dto.setIdConsulta(consulta.getIdConsulta());
        dto.setIdCita(consulta.getIdCita());
        dto.setDescripcion(consulta.getDescripcion());
        dto.setDiagnostico(consulta.getDiagnostico());
        dto.setReceta(consulta.getReceta());
        dto.setEstado(consulta.getEstado());
        return dto;
    }

    private Consulta convertToEntity(Consultadto dto) {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(dto.getIdConsulta());
        consulta.setIdCita(dto.getIdCita());
        consulta.setDescripcion(dto.getDescripcion());
        consulta.setDiagnostico(dto.getDiagnostico());
        consulta.setReceta(dto.getReceta());
        consulta.setEstado(dto.getEstado());
        return consulta;
    }
}