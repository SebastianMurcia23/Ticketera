package co.edu.uniquindio.proyecto.DTO;

public record RealizarPagoDTO(

        String idOrden,
        String tipoPago,
        double monto,
        String moneda

) {
}
