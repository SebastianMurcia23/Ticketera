package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.enums.TipoEvento;

public record FiltrarEvento_NombreTipoCiudad(

        String nombre,
        TipoEvento tipoEvento,
        String idCuidad

) {
}
