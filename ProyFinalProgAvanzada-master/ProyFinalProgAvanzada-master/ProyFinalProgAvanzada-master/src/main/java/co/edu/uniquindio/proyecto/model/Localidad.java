package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Localidad implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nombre;
    private double precio;
    private int capacidadMaxima;
    private int cantAsientosDisponibles;
    private List<Asiento> asientosLocalidad;

}
