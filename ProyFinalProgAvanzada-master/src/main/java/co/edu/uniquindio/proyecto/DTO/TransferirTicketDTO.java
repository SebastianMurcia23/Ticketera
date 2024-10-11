package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record TransferirTicketDTO(

        @NotBlank @Length(min = 4) String identificacionRemitente,
        @NotBlank @Length(min = 4) String identificacionDestinatario,
        @NotBlank @Length(min = 4) String codigoTicket

) {
}
