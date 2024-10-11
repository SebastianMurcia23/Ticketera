package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoPago;
import co.edu.uniquindio.proyecto.model.enums.MetodoPago;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("pagos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pago implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;


    private String estadoPago;
    private String tipoPago;
    private String moneda;
    private LocalDateTime fechaPago;
    private String detallePago;
    private double valorPago;
    private String codAutorizacion;
}
