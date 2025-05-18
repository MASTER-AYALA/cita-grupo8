package com.proyecto.Citas.Controller;

import com.proyecto.Citas.Entidades.Medico; // Importa la entidad Medico
import com.proyecto.Citas.Service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Importa la clase List
import java.util.Optional; // Importa la clase Optional

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() { // Corregido: ResponseEntity<List<Medico>>
        List<Medico> medicos = medicoService.listarMedicos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Integer id) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorId(id);
        if (medico.isPresent()) {
            return new ResponseEntity<>(medico.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Medico> guardarMedico(@RequestBody Medico medico) {
        Medico nuevoMedico = medicoService.guardarMedico(medico);
        return new ResponseEntity<>(nuevoMedico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Integer id, @RequestBody Medico medicoActualizado) {
        Optional<Medico> medicoExistente = medicoService.obtenerMedicoPorId(id);
        if (medicoExistente.isPresent()) {
            medicoActualizado.setIdMedico(id); // Asegura que el ID sea el correcto
            Medico medicoActualizadoGuardado = medicoService.guardarMedico(medicoActualizado);
            return new ResponseEntity<>(medicoActualizadoGuardado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Integer id) {
        medicoService.eliminarMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
