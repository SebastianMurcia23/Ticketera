package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document("eventos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Evento implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nombre;
    private String direccion;
    private String ciudad;
    private String descripcion;
    private TipoEvento tipoEvento;
    private String poster;
    private String imgBoleta;
    private String distribucionLocalidades;
    private LocalDate fechaEvento;
    private List<Localidad> localidades;
    private EstadoEvento estadoEvento;





}
