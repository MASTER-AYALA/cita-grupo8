package com.proyecto.Citas.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;



@Data

@Entity
@Table(name = "Medico", schema = "clinica") // Especificar el esquema
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico", nullable = false)
    private Integer idMedico;

    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "id_especialidad", nullable = false)
    private Integer idEspecialidad;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}