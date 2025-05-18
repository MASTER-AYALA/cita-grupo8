package com.proyecto.Citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuariodto {
    private Integer idusuario;
    private String nombre;
    private String password;
}