package com.proyecto.Citas.Controller;

import com.proyecto.Citas.dto.Pacientedto;
import com.proyecto.Citas.Entidades.Paciente;
import com.proyecto.Citas.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Pacientedto>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        List<Pacientedto> pacienteDtos = pacientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(pacienteDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacientedto> obtenerPacientePorId(@PathVariable Integer id) {
        Optional<Paciente> paciente = pacienteService.obtenerPacientePorId(id);
        if (paciente.isPresent()) {
            return new ResponseEntity<>(convertToDto(paciente.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pacientedto> guardarPaciente(@RequestBody Pacientedto pacientedto) {
        Paciente paciente = convertToEntity(pacientedto);
        Paciente nuevoPaciente = pacienteService.guardarPaciente(paciente);
        return new ResponseEntity<>(convertToDto(nuevoPaciente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacientedto> actualizarPaciente(@PathVariable Integer id, @RequestBody Pacientedto pacientedto) {
        Optional<Paciente> pacienteExistente = pacienteService.obtenerPacientePorId(id);
        if (pacienteExistente.isPresent()) {
            Paciente paciente = convertToEntity(pacientedto);
            paciente.setIdPaciente(id);
            Paciente pacienteActualizadoGuardado = pacienteService.guardarPaciente(paciente);
            return new ResponseEntity<>(convertToDto(pacienteActualizadoGuardado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Pacientedto convertToDto(Paciente paciente) {
        Pacientedto dto = new Pacientedto();
        dto.setIdPaciente(paciente.getIdPaciente());
        dto.setNombres(paciente.getNombres());
        dto.setApellidos(paciente.getApellidos());
        dto.setPeso(paciente.getPeso());
        dto.setAltura(paciente.getAltura());
        dto.setEdad(paciente.getEdad());
        dto.setTelefono(paciente.getTelefono());
        dto.setAlergias(paciente.getAlergias());
        dto.setEnfermedades(paciente.getEnfermedades());
        dto.setTipoSangre(paciente.getTipoSangre());
        dto.setEstado(paciente.getEstado());
        return dto;
    }

    private Paciente convertToEntity(Pacientedto dto) {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(dto.getIdPaciente());
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setPeso(dto.getPeso());
        paciente.setAltura(dto.getAltura());
        paciente.setEdad(dto.getEdad());
        paciente.setTelefono(dto.getTelefono());
        paciente.setAlergias(dto.getAlergias());
        paciente.setEnfermedades(dto.getEnfermedades());
        paciente.setTipoSangre(dto.getTipoSangre());
        paciente.setEstado(dto.getEstado());
        return paciente;
    }
}