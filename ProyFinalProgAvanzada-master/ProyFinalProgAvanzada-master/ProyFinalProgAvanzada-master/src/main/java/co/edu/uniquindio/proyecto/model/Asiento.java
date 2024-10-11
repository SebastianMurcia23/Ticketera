package co.edu.uniquindio.proyecto.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Asiento {

    private int idAsiento;
    private boolean disponibilidad;//true si esta disponible false si no
    private String numeroAsiento;
    private String usuarioCompra;
    private String idTicket;
}
