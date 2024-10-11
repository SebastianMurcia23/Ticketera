package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CrearCuponDTO(

        @NotBlank @Length(min = 4) String codigoUsable,
         LocalDateTime fechaVencimiento,
         int usosPermitidos,
         TipoCupon tipoCupon,
         Double porcentajeCupon,
        @NotBlank String descripcion

) {
}
