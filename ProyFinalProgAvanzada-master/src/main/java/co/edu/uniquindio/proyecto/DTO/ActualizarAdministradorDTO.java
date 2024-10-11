package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.CodigoValidacion;
import co.edu.uniquindio.proyecto.model.Cuenta;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizarAdministradorDTO(
        @NotBlank @Length(min = 4) String identificacion
) {
}
