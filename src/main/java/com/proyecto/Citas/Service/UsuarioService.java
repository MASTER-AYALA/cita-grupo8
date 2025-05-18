package com.proyecto.Citas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.proyecto.Citas.Entidades.Usuario;
import com.proyecto.Citas.Repository.UsuarioRepository;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        if(usuario.getVersion() == null) {
            usuario.setVersion(0);
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizar(Integer id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(existente -> {
                    if(usuario.getVersion() != null && 
                       !Objects.equals(existente.getVersion(), usuario.getVersion())) {
                        throw new ObjectOptimisticLockingFailureException(
                            Usuario.class, 
                            existente.getId()
                        );
                    }
                    
                    existente.setNombreUsuario(usuario.getNombreUsuario());
                    existente.setContraseñaUsuario(usuario.getContraseñaUsuario());
                    existente.setRolUsuario(usuario.getRolUsuario());
                    
                    if(usuario.getEstado() != null) {
                        existente.setEstado(usuario.getEstado());
                    }
                    
                    return usuarioRepository.save(existente);
                })
                .orElse(null);
    }

    @Transactional
    public boolean eliminar(Integer id, Integer version) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    if(version != null && !Objects.equals(usuario.getVersion(), version)) {
                        throw new ObjectOptimisticLockingFailureException(
                            Usuario.class, 
                            usuario.getId()
                        );
                    }
                    
                    usuarioRepository.delete(usuario);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}