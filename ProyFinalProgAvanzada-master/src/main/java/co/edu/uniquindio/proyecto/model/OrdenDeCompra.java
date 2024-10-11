package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoCompra;
import co.edu.uniquindio.proyecto.model.enums.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document("ordenesDeCompra")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdenDeCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String idCliente;
    private LocalDateTime fechaCreacion;
    private EstadoCompra estadoCompra;
    private List<ItemCompra> items;
    private String idPago;
    private double total;
    private String idCupon;
    private String codigoPasarela;

}
