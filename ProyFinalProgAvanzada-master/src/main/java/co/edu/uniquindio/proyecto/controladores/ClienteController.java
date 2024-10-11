package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.services.interfaces.I_ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClienteController {

    private final I_ClienteService i_clienteService;

    @PostMapping("/registrarse")
    ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody RegistrarClienteDTO registrarClienteDTO)throws Exception{

        String mensaje = i_clienteService.registrarse(registrarClienteDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));

    }
    @PutMapping("/editar-perfil")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO)throws Exception{

        String mensaje = i_clienteService.editarPerfil(actualizarClienteDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
    @DeleteMapping("/eliminar")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> borrarCuenta(@Valid @RequestBody EliminarCuentaDTO eliminarCuentaDTO)throws Exception{
        String mensaje = i_clienteService.borrarCuenta(eliminarCuentaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }

    @GetMapping("/filtrar-Eventos")
    public ResponseEntity<MensajeDTO<List<InfoPrincipalEventDTO>>> filtrarEvento(@Valid @RequestBody FiltrarEvento_NombreTipoCiudad filtroEventoDTO)throws Exception{
       // return i_clienteService.filtrarEvento(filtroEventoDTO);
        List<InfoPrincipalEventDTO> nuevoList=new ArrayList<>();
        nuevoList=i_clienteService.filtrarEvento(filtroEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, nuevoList));
    }
    @PutMapping("/transferir-Ticket")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> transferirTicket(@Valid @RequestBody TransferirTicketDTO transferirTicketDTO)throws Exception{
       // return
        String mensaje = i_clienteService.transferirTicket(transferirTicketDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }

    @PutMapping("/aceptar-Transferencia")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> aceptarTransferencia(@Valid @RequestBody RespuestaTransferenciaDTO respuestaTransferenciaDTO)throws Exception{

        String mensaje = i_clienteService.aceptarTransferencia(respuestaTransferenciaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
    @PutMapping("/rechazar-Transferencia")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> rechazarTransferencia(@Valid @RequestBody RespuestaTransferenciaDTO respuestaTransferenciaDTO)throws Exception{

        String mensaje = i_clienteService.rechazarTransferencia(respuestaTransferenciaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }



    @PutMapping("/redimir-Cupon")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> redimirCupon(@Valid @RequestBody RedimirCuponDTO redimirCuponDTO)throws Exception{

        String mensaje = i_clienteService.redimirCupon(redimirCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }

    @PutMapping("/quitar-Cupon/{identificacionClient}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> quitarCupon(@PathVariable String identificacionClient)throws Exception{

        String mensaje = i_clienteService.quitarCupon(identificacionClient);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }

    @PutMapping("/anadirItem-Carrito")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> anadirACarrito(@Valid @RequestBody ItemCompraDTO itemCompraDTO)throws Exception{

        String mensaje = i_clienteService.anadirACarrito(itemCompraDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
    @PutMapping("/quitarItem-Carrito")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> quitarDeCarrito(@Valid @RequestBody SeleccionItemsCompraDTO seleccionItemsCompraDTO)throws Exception{

        String mensaje = i_clienteService.quitarDeCarrito(seleccionItemsCompraDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
    @PutMapping("/vaciar-Carrito/{identificacionClient}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> vaciarCarrito(@PathVariable String identificacionCliente)throws Exception{

        String mensaje = i_clienteService.vaciarCarrito(identificacionCliente);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
    @PostMapping("/crear-PQRS")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> crearPQRS(@Valid @RequestBody CrearPQRS_DTO crearPQRS_DTO)throws Exception{

        String mensaje = i_clienteService.crearPQRS(crearPQRS_DTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
    @PutMapping("/cancelar-PQRS")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> cancelarPQRS(@Valid @RequestBody CancelarPQRS_DTO cancelarPQRS_dto)throws Exception{

        String mensaje = i_clienteService.cancelarPQRS(cancelarPQRS_dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
    }
}
