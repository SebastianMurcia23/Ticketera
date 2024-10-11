package co.edu.uniquindio.proyecto.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

       @NotBlank @Email String usuario,
       @NotBlank String password

) {
}
