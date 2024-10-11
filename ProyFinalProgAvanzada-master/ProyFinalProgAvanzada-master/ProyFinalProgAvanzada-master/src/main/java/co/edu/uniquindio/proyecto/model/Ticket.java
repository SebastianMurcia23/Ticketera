package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String codigoEvento;
    private String nombreEvento;
    private LocalDate fechaHora;
    private String localidad;
    private String qr;

}
