package co.edu.uniquindio.proyecto.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ItemCompraDTO(
        @NotBlank @Length(min = 4) String identificacionCliente,
        @NotBlank @Length(min = 4) String codigoEvento,
        @NotBlank @Length(min = 4) String localidad,
          List<String>idAsientosLocalidad
       // @NotBlank  int cantidad,
        //@NotBlank  Double precioUnitario


) {
}
