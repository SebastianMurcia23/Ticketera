package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String localidad;
    private int cantidad;
    private Double precioUnitario;
}
