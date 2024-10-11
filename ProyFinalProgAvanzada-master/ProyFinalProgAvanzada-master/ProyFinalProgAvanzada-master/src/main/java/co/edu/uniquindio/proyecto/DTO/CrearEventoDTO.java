package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.Localidad;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record CrearEventoDTO(

        @NotBlank @Length(min = 4, max = 100) String nombre,
        @NotBlank @Length(min = 4, max = 60)String direccion,
        @NotBlank String ciudad,
         String descripcion,
         TipoEvento tipoEvento,
         String poster,
         String imgBoleta,
         String imgDistribucionLocalidades,
         LocalDate fechaEvento,
         List<Localidad>localidades

) {
}
