package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusquedaCuponDTO(

        @NotBlank @NotNull String codUsable
) {
}
