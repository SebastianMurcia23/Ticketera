package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.checkerframework.checker.units.qual.N;

public record ValidarCodigoDTO(

        @NotBlank @Email String emailClient,
        @NotBlank String codVerif

) {
}
