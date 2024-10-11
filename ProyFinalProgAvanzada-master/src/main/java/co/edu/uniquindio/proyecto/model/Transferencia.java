package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoTransferencia;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Document("transferencias")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transferencia implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private LocalDateTime fechaHora;
    private String remitente;
    private String destinatario;
    private String ticket;
    private EstadoTransferencia estadoTransferencia;
}
