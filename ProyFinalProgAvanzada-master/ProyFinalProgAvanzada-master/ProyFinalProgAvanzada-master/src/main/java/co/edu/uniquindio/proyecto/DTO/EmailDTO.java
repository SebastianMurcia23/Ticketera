package co.edu.uniquindio.proyecto.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        @NotBlank  String asunto,
        @NotBlank @Email String destinatario,
        @NotBlank @Length(max=2000) String mensaje
) {
}
