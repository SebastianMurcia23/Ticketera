package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RespuestaTransferenciaDTO(

        @NotBlank @Length(min = 4) String codigoTransferencia

) {
}
