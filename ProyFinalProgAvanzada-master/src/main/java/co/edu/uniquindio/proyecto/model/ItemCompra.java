package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String codigoEvento;
    private String localidad;
    private List<String> idAsientosLocalidad;
    private int cantidad;
    private Double precioUnitario;
}
