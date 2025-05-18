package com.proyecto.Citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Especialidaddto {
    private Integer idEspecialidad;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}