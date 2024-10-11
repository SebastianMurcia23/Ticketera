package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RestablecerContrasenaDTO(


         @NotBlank @Email String emailClient

        ) {
}
