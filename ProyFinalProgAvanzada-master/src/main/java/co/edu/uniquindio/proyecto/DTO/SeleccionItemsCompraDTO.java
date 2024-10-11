package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record SeleccionItemsCompraDTO(

        @NotBlank @Length(min = 4) String identificacion,
        @NotNull List<String> idsItemsSeleccionados

) {
}
