package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.security.SecureRandom;

@Document("administradores")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Administrador implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String identificacion;
    private String nombreCompleto;
    private Cuenta cuenta;
    private String email;
    private CodigoValidacion codigoValidacion;


}
