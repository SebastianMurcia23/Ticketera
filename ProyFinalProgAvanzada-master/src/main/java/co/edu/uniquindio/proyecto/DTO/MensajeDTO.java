package co.edu.uniquindio.proyecto.DTO;
public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
