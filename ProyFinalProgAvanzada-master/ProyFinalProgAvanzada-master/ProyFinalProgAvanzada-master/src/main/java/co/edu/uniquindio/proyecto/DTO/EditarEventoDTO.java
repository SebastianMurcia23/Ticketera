package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.Localidad;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;

import java.time.LocalDate;
import java.util.List;

public record EditarEventoDTO(

        String nombre,
        String direccion,
        String ciudad,
        String descripcion,
        TipoEvento tipoEvento,
        String poster,
        String imgBoleta,
        String imgDistribucionLocalidades,
        LocalDate fechaEvento,
        List<Localidad> localidades

) {
}
