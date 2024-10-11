package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RedimirCuponDTO(
        //@NotNull String codigoOrdenCompra,
        @NotNull @NotBlank String identificacion,
        @NotNull String codigoUsable
) {
}
