package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;

public record EliminarCuponDTO(

        @NotBlank String idCupon

) {
}
