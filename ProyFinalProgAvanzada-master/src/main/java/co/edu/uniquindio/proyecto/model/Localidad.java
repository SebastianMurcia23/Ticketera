package co.edu.uniquindio.proyecto.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Localidad implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;


    private String nombreLocalidad;
    private double precio;
    private int capacidadMaxima;
    private int cantAsientosDisponibles;
    private List<Asiento> asientosLocalidad;



    public boolean validarDisponibilidadAsientos( List<String> idsAsientos) {
            // Filtrar los asientos de la localidad que coinciden con los ids proporcionados y están disponibles
            List<Asiento> asientosSolicitados = this.asientosLocalidad.stream()
                    .filter(asiento -> idsAsientos.contains(asiento.getIdAsiento()) && asiento.isDisponible())
                    .collect(Collectors.toList());

            // Verificar si todos los asientos solicitados están disponibles (ya filtrados como disponibles)
            return asientosSolicitados.size() == idsAsientos.size();
    }

//      for (String aSelected:idsAsientos){
//        if (aSelected!=null){
//            for (Asiento a:this.asientosLocalidad){
//                if(a!=null){
//                    if (a.getIdAsiento().equalsIgnoreCase(aSelected)){
//                        if(a.isDisponible()){
//
//                        }
//                    }
//                }
//            }
//        }
//    }




//    // Filtrar los asientos de la localidad que coinciden con los ids proporcionados
//    List<Asiento> asientosSolicitados = this.asientosLocalidad.stream()
//            .filter(asiento -> idsAsientos.contains(asiento.getIdAsiento()))
//            .collect(Collectors.toList());
//
//    // Verificar si todos los asientos solicitados están disponibles
//        return asientosSolicitados.stream().allMatch(Asiento::isDisponible);
}
