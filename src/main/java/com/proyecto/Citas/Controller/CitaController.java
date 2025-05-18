package com.proyecto.Citas.Controller;

import com.proyecto.Citas.dto.Citadto;
import com.proyecto.Citas.Entidades.Cita;
import com.proyecto.Citas.Service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Obtiene todas las citas
    @GetMapping
    public ResponseEntity<List<Citadto>> obtenerTodasLasCitas() {
        List<Cita> citas = citaService.obtenerTodasLasCitas();
        List<Citadto> citasDto = citas.stream()
                                      .map(this::convertToDto)
                                      .toList();
        return new ResponseEntity<>(citasDto, HttpStatus.OK);
    }

    // Obtiene una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Citadto> obtenerCitaPorId(@PathVariable Integer id) {
        Optional<Cita> cita = citaService.obtenerCitaPorId(id);
        return cita.map(c -> new ResponseEntity<>(convertToDto(c), HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Guarda una nueva cita
    @PostMapping
    public ResponseEntity<Citadto> guardarCita(@RequestBody Citadto citaDto) {
        Cita cita = convertToEntity(citaDto);
        Cita nuevaCita = citaService.guardarCita(cita);
        return new ResponseEntity<>(convertToDto(nuevaCita), HttpStatus.CREATED);
    }

    // Actualiza una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<Citadto> actualizarCita(@PathVariable Integer id, @RequestBody Citadto citaDto) {
        Optional<Cita> citaExistente = citaService.obtenerCitaPorId(id);
        if (citaExistente.isPresent()) {
            Cita cita = convertToEntity(citaDto);
            cita.setIdCita(id);
            Cita citaActualizada = citaService.guardarCita(cita);
            return new ResponseEntity<>(convertToDto(citaActualizada), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Elimina una cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Integer id) {
        citaService.eliminarCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Conversión de Cita a Citadto
    private Citadto convertToDto(Cita cita) {
        Citadto dto = new Citadto();
        dto.setIdCita(cita.getIdCita());
        dto.setFecha(cita.getFecha());
        dto.setHora(cita.getHora());
        dto.setDia(cita.getDia());
        dto.setSemana(cita.getSemana());
        dto.setIdMedico(cita.getIdMedico());
        dto.setIdPaciente(cita.getIdPaciente());
        dto.setEstado(cita.getEstado());
        return dto;
    }

    // Conversión de Citadto a Cita
    private Cita convertToEntity(Citadto dto) {
        Cita cita = new Cita();
        cita.setIdCita(dto.getIdCita());
        cita.setFecha(dto.getFecha());
        cita.setHora(dto.getHora());
        cita.setDia(dto.getDia());
        cita.setSemana(dto.getSemana());
        cita.setIdMedico(dto.getIdMedico());
        cita.setIdPaciente(dto.getIdPaciente());
        cita.setEstado(dto.getEstado());
        return cita;
    }
}
