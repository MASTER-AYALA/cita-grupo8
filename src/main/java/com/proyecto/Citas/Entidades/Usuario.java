package com.proyecto.Citas.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia para PostgreSQL
    @Column(name = "id")
    private Integer id;

    @Version
    @Column(name = "version", nullable = false, columnDefinition = "integer default 0")
    private Integer version = 0;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    @Column(name = "contraseña_usuario", nullable = false)
    private String contraseñaUsuario;

    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 20, message = "El rol no puede exceder 20 caracteres")
    @Column(name = "rol_usuario", nullable = false)
    private String rolUsuario;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado", nullable = false, columnDefinition = "boolean default true")
    private Boolean estado = true;
}