package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelarPQRS_DTO(

        @NotBlank @NotNull String codigoPQRS

) {
}
