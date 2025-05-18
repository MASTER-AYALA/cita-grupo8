package com.proyecto.Citas.Entidades;

import jakarta.persistence.*;

import lombok.Data;



@Data
@Entity
@Table(name = "Especialidad", schema = "clinica")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

}