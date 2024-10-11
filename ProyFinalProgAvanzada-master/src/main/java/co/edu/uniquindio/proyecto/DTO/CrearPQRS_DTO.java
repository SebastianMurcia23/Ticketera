package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.enums.TipoSolicitud;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CrearPQRS_DTO(

        @NotBlank @Length(min = 4) String identificacionAutor,
        @NotNull TipoSolicitud tipoSolicitud,
        @NotBlank  String titulo,
        @NotBlank  String mensaje


) {
}
