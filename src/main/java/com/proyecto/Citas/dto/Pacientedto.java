package com.proyecto.Citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Pacientedto {
        private Integer idPaciente; 
        private String nombres; 
        private String apellidos;
        private Double peso; 
        private Double altura; 
        private Integer edad;
        private String telefono; 
        private String alergias;
        private String enfermedades;
        private String tipoSangre; 
        private Boolean estado; 

}
