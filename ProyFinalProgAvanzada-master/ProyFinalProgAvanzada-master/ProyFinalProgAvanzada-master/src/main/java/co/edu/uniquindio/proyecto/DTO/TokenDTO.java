package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token
){
}