package com.proyecto.Citas.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Cita", schema = "clinica")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "dia")
    private String dia;

    @Column(name = "semana")
    private Integer semana;

    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "estado")
    private Boolean estado;
    
    @Column(name = "nombre")
    private String nombre;

}