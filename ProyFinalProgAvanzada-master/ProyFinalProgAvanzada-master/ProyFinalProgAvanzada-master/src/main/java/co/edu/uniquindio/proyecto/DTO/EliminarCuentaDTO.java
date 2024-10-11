package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EliminarCuentaDTO(

        @NotBlank @Length(min = 4) String idCLiente,
        @NotBlank @Length(min = 4) String identificacion

) {
}
