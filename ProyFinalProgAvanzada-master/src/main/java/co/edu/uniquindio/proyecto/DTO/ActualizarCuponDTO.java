package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.model.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ActualizarCuponDTO(


        @NotBlank String idCodCupon,
        @NotBlank LocalDateTime fechaVencimiento,
        @NotBlank int usosPermitidos,
        @NotBlank Double porcentajeCupon,
        @NotBlank String descripcion,
        @NotNull EstadoCupon estadoCupon
        ) {
}
