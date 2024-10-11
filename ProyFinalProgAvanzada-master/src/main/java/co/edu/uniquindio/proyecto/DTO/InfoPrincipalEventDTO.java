package co.edu.uniquindio.proyecto.DTO;

import java.time.LocalDateTime;

public record InfoPrincipalEventDTO (

        String idEvent,
        String imgPortada,
        String nombre,
        String ciudad,
        LocalDateTime fecha,
        String descripcion

){
}
