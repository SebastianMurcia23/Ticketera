package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Asiento {
    @Id
    @EqualsAndHashCode.Include
    private String idAsiento;
    private boolean disponible;//true si esta disponible false si no
    private String numeroAsiento;
    private String usuarioCompra;
    private String idTicket;
}
