package com.proyecto.Citas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.Citas.Entidades.Usuario;
import com.proyecto.Citas.Service.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok()
                    .eTag(usuario.getVersion().toString())
                    .body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id,
            @RequestBody Usuario usuario,
            @RequestHeader(value = "If-Match", required = false) Integer versionEsperada) {
        
        try {
            if (versionEsperada != null) {
                usuario.setVersion(versionEsperada);
            }
            
            Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok()
                    .eTag(usuarioActualizado.getVersion().toString())
                    .body(usuarioActualizado);
            
        } catch (Exception e) {
            return manejarExcepcionConcurrencia(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Integer id,
            @RequestHeader(value = "If-Match", required = false) Integer versionEsperada) {
        
        try {
            boolean eliminado = usuarioService.eliminar(id, versionEsperada);
            return eliminado ? 
                    ResponseEntity.noContent().build() : 
                    ResponseEntity.notFound().build();
                    
        } catch (Exception e) {
            return manejarExcepcionConcurrencia(e);
        }
    }

    private ResponseEntity<String> manejarExcepcionConcurrencia(Exception e) {
        if (e instanceof org.springframework.orm.ObjectOptimisticLockingFailureException) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de concurrencia: El recurso fue modificado por otro usuario. Por favor refresque los datos.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + e.getMessage());
    }
}