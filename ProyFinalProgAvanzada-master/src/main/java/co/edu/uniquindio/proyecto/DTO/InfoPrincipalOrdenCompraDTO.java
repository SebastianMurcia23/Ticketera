package co.edu.uniquindio.proyecto.DTO;

import co.edu.uniquindio.proyecto.model.ItemCompra;
import co.edu.uniquindio.proyecto.model.enums.EstadoCompra;

import java.time.LocalDateTime;
import java.util.List;

public record InfoPrincipalOrdenCompraDTO(

         String codOrden,
         EstadoCompra estadoCompra,
         LocalDateTime fechaCreacion,
         double total



) {
}
