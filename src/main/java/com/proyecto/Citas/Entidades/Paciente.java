package com.proyecto.Citas.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor


@Entity
@Table (name = "Paciente", schema = "clinica")
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "enfermedades")
    private String enfermedades;

    @Column(name = "tipo_sangre")
    private String tipoSangre;

    @Column(name = "estado")
    private Boolean estado;
}
