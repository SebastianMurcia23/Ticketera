package co.edu.uniquindio.proyecto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@ToString
public class CodigoValidacion implements Serializable {

   private String codigo;
   private LocalDateTime fechaCreacion;

   public CodigoValidacion() {
      Random random = new Random();
      this.codigo = ""+100000 + random.nextInt(900000);;
      this.fechaCreacion = LocalDateTime.now();
   }
}
