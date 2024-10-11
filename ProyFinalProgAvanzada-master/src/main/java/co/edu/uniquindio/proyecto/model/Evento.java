package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime fechaEvento;
    private List<Localidad> localidades;
    private EstadoEvento estadoEvento;


    public Localidad obtenerLocalidad(@NotBlank @Length(min = 4) String localidad) {
        for (Localidad l : localidades) {
            if (l!=null){
                if (l.getCodigo().equalsIgnoreCase(localidad)){
                    return l;
                }
            }
        }
        return null;
    }
}
