package co.edu.uniquindio.proyecto.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String identificacion;
    private String nombreCompleto;
    private String email;
    private Cuenta cuenta;
    private String direccion;
    private String telefono;
    private CodigoValidacion codigoValidacion;
    private List<Transferencia> misTransferencias;
    private List<Ticket>misTickets;
    private List<Cupon>misCupones;


    @Builder
    public Cliente(String codigo, String identificacion, String nombreCompleto, String email, Cuenta cuenta, String direccion, String telefono, CodigoValidacion codigoValidacion, List<Transferencia> misTransferencias, List<Ticket> misTickets, List<Cupon> misCupones) {
        this.codigo = codigo;
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.cuenta = cuenta;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoValidacion = codigoValidacion;
        this.misTransferencias = misTransferencias;
        this.misTickets = misTickets;
        this.misCupones = misCupones;
    }
}
