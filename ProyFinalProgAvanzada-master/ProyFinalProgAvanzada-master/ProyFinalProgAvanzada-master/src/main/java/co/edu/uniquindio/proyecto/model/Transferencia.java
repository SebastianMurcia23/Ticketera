package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoTransferencia;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transferencia implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private LocalDate fechaHora;
    private String destinatario;
    private String ticket;
    private EstadoTransferencia estadoTransferencia;
}
