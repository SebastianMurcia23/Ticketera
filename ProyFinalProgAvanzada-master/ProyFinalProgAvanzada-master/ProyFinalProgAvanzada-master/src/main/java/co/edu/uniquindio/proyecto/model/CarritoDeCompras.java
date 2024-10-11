package co.edu.uniquindio.proyecto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarritoDeCompras implements Serializable {

    private List<ItemCompra> listItemsCompra;


}
