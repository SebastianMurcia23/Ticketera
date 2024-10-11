package co.edu.uniquindio.proyecto.DTO;


import co.edu.uniquindio.proyecto.model.enums.TipoEvento;

import java.time.LocalDateTime;

public record FiltrarEvento_NombreTipoCiudad(

        String nombre,
        String ciudad,
        TipoEvento tipoEvento,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin

) {
}
