package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.enums.EstadoPQRS;

public record DarEstadoPQRS(

        String idPQRS,
        String idAdmin,
        EstadoPQRS estadoPQRS
) {
}
