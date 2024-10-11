package co.edu.uniquindio.proyecto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@ToString
public class CarritoDeCompras implements Serializable {

    private List<ItemCompra> listItemsCompra;
    private String cupon;
    private Double total;

    public CarritoDeCompras() {
        this.listItemsCompra = new ArrayList<>();
        this.cupon = "";
        this.total = 0.0;
    }

    public boolean validarExistenItems(List<String> idsItemsSelecionados) {

        // Verificar que todos los ids seleccionados existan en la lista de itemsCompra
        return idsItemsSelecionados.stream()
                .allMatch(id -> listItemsCompra.stream()
                        .anyMatch(item -> item.getCodigo().equals(id)));
    }

    public ItemCompra obtenerItemCompra(String idActual) {

        for (ItemCompra item : listItemsCompra){
            if (item!=null) {
                if (item.getCodigo().equalsIgnoreCase(idActual)) {
                    return item;
                }
            }
        }
        return null;
    }


}
