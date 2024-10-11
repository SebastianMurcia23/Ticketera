package co.edu.uniquindio.proyecto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CodigoValidacion implements Serializable {

   private String codigo;
   private LocalDate fechaCreación;
}
