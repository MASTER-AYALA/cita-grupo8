package com.proyecto.Citas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Medico {
   private Integer idmedico;
   private String nombres;
   private Integer idespecialidad;
   private Integer idusuario;
   private String estado;


}
