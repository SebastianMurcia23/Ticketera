package co.edu.uniquindio.proyecto.model;


import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.model.enums.TipoCupon;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Document("cupones")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String codigoUsable;
    private LocalDateTime fechaVencimiento;
    private int usosPermitidos;
    private int usosRealizados;
    private EstadoCupon estadoCupon;
    private TipoCupon tipoCupon;
    private Double porcentajeCupon;
    private String descripcion;

    public boolean validarEstadoCupon(LocalDateTime fechaVencimiento) {
        if(LocalDateTime.now().isBefore(fechaVencimiento)) {
           return false;
        }
        return true;
    }
}
