package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensaje {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String idAutor;
    private String Titulo;
    private String mensaje;
}
