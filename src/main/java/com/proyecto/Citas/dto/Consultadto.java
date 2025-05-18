package com.proyecto.Citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultadto {

    private Integer idConsulta;
    private Integer idCita;
    private String descripcion;
    private String diagnostico;
    private String receta;
    private Boolean estado;
}