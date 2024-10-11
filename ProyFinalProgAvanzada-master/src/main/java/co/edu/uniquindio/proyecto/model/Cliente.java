package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.enums.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
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
    private boolean primerCompra;
    private TipoUsuario tipoUsuario;
    private CarritoDeCompras carritoDeCompras;
    private CodigoValidacion codigoValidacion;
    private List<Transferencia> misTransferenciasEnviadas;
    private List<Transferencia> misTransferenciasRecibidas;
    private List<Ticket>misTickets;
    private List<Cupon>misCupones;
    private List<String>MisOrdenesCompra;


    @Builder
    public Cliente(String codigo, String identificacion, String nombreCompleto, String email, Cuenta cuenta, String direccion, String telefono, boolean primerCompra, TipoUsuario tipoUsuario, CarritoDeCompras carritoDeCompras, CodigoValidacion codigoValidacion, List<Transferencia> misTransferenciasEnviadas, List<Transferencia> misTransferenciasRecibidas, List<Ticket> misTickets, List<Cupon> misCupones, List<String> misOrdenesCompra) {
        this.codigo = codigo;
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.cuenta = cuenta;
        this.direccion = direccion;
        this.telefono = telefono;
        this.primerCompra = primerCompra;
        this.tipoUsuario = tipoUsuario;
        this.carritoDeCompras = carritoDeCompras;
        this.codigoValidacion = codigoValidacion;
        this.misTransferenciasEnviadas = misTransferenciasEnviadas;
        this.misTransferenciasRecibidas = misTransferenciasRecibidas;
        this.misTickets = misTickets;
        this.misCupones = misCupones;
        MisOrdenesCompra = misOrdenesCompra;
    }




    public boolean validarSiTieneTicket(@NotBlank @Length(min = 4) String s) {

        for (Ticket ticket : misTickets) {
            if (ticket.getCodigo().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public Ticket obtenerTicket(@NotBlank @Length(min = 4) String s) {
        for (Ticket ticket : misTickets) {
            if (ticket.getCodigo().equalsIgnoreCase(s)) {
                return ticket;
            }
        }
        return null;
    }
}
