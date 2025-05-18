package com.proyecto.Citas.Controller;

import com.proyecto.Citas.dto.Especialidaddto;
import com.proyecto.Citas.Entidades.Especialidad;
import com.proyecto.Citas.Service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<Especialidaddto>> listarEspecialidades() {
        List<Especialidad> especialidades = especialidadService.listarEspecialidades();
        List<Especialidaddto> especialidadDtos = especialidades.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(especialidadDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidaddto> obtenerEspecialidadPorId(@PathVariable Integer id) {
        Optional<Especialidad> especialidad = especialidadService.obtenerEspecialidadPorId(id);
        if (especialidad.isPresent()) {
            return new ResponseEntity<>(convertToDto(especialidad.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Especialidaddto> guardarEspecialidad(@RequestBody Especialidaddto especialidadDto) {
        Especialidad especialidad = convertToEntity(especialidadDto);
        Especialidad nuevaEspecialidad = especialidadService.guardarEspecialidad(especialidad);
        return new ResponseEntity<>(convertToDto(nuevaEspecialidad), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidaddto> actualizarEspecialidad(@PathVariable Integer id, @RequestBody Especialidaddto especialidadDto) {
        Optional<Especialidad> especialidadExistente = especialidadService.obtenerEspecialidadPorId(id);
        if (especialidadExistente.isPresent()) {
            Especialidad especialidad = convertToEntity(especialidadDto);
            especialidad.setIdEspecialidad(id);
            Especialidad especialidadActualizadaGuardada = especialidadService.guardarEspecialidad(especialidad);
            return new ResponseEntity<>(convertToDto(especialidadActualizadaGuardada), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEspecialidad(@PathVariable Integer id) {
        especialidadService.eliminarEspecialidad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Especialidaddto convertToDto(Especialidad especialidad) {
        Especialidaddto dto = new Especialidaddto();
        dto.setIdEspecialidad(especialidad.getIdEspecialidad());
        dto.setNombre(especialidad.getNombre());
        dto.setDescripcion(especialidad.getDescripcion());
        dto.setEstado(especialidad.getEstado());
        return dto;
    }

    private Especialidad convertToEntity(Especialidaddto dto) { // Corregido: eliminada la coma extra
        Especialidad especialidad = new Especialidad();
        especialidad.setIdEspecialidad(dto.getIdEspecialidad());
        especialidad.setNombre(dto.getNombre());
        especialidad.setDescripcion(dto.getDescripcion());
        especialidad.setEstado(dto.getEstado());
        return especialidad;
    }
}