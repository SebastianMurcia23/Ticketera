package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.model.enums.TipoSolicitud;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("pqrs")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PQRS implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String idCliente;
    private TipoSolicitud tipoSolicitud;
    private String descripcion;
    private EstadoPQRS estadoPQRS;
    private List<Mensaje> respuestas;
    private String idAdminAtiende;

}
