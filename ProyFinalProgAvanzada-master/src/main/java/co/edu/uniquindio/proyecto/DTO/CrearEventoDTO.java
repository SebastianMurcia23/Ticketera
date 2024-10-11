package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.Localidad;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CrearEventoDTO(

        @NotBlank @Length(min = 4, max = 100) String nombre,
        @NotBlank @Length(min = 4, max = 60)String direccion,
        @NotBlank String ciudad,
        @NotBlank String descripcion,
        @NotNull TipoEvento tipoEvento,
        @NotBlank String poster,
        @NotBlank String imgBoleta,
        @NotBlank String imgDistribucionLocalidades,
         LocalDateTime fechaEvento,
         List<Localidad>localidades

) {
}
