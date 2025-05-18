package com.proyecto.Citas.dto;


import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Citadto {


   private Integer idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String dia;
    private String nombre;
    private Integer semana;
    private Integer idMedico;
    private Integer idPaciente;
    private Boolean estado;
  
}
