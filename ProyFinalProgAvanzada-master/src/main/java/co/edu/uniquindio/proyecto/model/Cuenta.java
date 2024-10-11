package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoCuenta;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String usuario;
    private String password;
    private EstadoCuenta estadoCuenta;
}
