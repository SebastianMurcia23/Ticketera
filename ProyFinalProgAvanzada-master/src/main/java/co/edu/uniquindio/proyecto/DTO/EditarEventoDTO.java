package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.Localidad;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EditarEventoDTO(
        String codEvent,
        String nombre,
        String direccion,
        String ciudad,
        String descripcion,
        TipoEvento tipoEvento,
        String poster,
        String imgBoleta,
        String imgDistribucionLocalidades,
        LocalDateTime fechaEvento,
        List<Localidad> localidades


) {
}
