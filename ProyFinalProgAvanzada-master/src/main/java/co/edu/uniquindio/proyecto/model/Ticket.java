package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime fechaHora;
    private String localidad;
    private String qr;
    private String asiento;

}
