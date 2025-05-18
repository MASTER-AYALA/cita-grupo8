package com.proyecto.Citas.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "Consulta", schema = "clinica")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Integer idConsulta;

    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "receta")
    private String receta;

    @Column(name = "estado")
    private Boolean estado;

}