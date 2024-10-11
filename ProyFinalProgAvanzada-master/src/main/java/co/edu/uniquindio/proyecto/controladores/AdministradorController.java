package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.model.Cupon;
import co.edu.uniquindio.proyecto.services.interfaces.I_AdministradorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdministradorController {

    private final I_AdministradorService iAdministradorService;


    @PostMapping("/crear-Evento")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> crearEvento(@Valid @RequestBody CrearEventoDTO crearEventoDTO)throws Exception{
        String cadena= iAdministradorService.crearEvento(crearEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @PutMapping ("/moificar-Evento")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> modificarEvento(@Valid @RequestBody EditarEventoDTO editarEventoDTO)throws Exception{
        String cadena= iAdministradorService.modificarEvento(editarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @DeleteMapping("/eliminar-Evento")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> eliminarEvento(@Valid @RequestBody EliminarEventoDTO eliminarEventoDTO)throws Exception{
        String cadena= iAdministradorService.eliminarEvento(eliminarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @GetMapping("/listar-Eventos")
    public ResponseEntity<MensajeDTO<List<InfoPrincipalEventDTO>>>listarEventos()throws Exception{
    List<InfoPrincipalEventDTO> listInfo=new ArrayList<>();
    listInfo=iAdministradorService.listarEventos();
        return ResponseEntity.ok(new MensajeDTO<>(false, listInfo));
    }
    void generarReportePorCorte()throws Exception{

    }
    @PostMapping("/crear-Cupon")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> crearCupon(@Valid @RequestBody CrearCuponDTO crearCuponDTO)throws Exception{
        String cadena= iAdministradorService.crearCupon(crearCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @DeleteMapping("/eliminar-Cupon")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> eliminarCupon(@Valid @RequestBody EliminarCuponDTO eliminarCuponDTO)throws Exception{
        String cadena= iAdministradorService.eliminarCupon(eliminarCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @PutMapping ("/actualizar-Cupon")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> actualizarCupon(@Valid @RequestBody ActualizarCuponDTO cupon)throws Exception{
        String cadena= iAdministradorService.actualizarCupon(cupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @GetMapping("/listar-Cupones")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<List<Cupon>>> listarCupones()throws Exception{
        List<Cupon>listCupon=new ArrayList<>();
        listCupon=iAdministradorService.listarCupones();
        return ResponseEntity.ok(new MensajeDTO<>(false, listCupon));
    }
    @PutMapping("/buscar-Cupon")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<Cupon>> buscarCupon(@Valid @RequestBody BusquedaCuponDTO busquedaCuponDTO)throws Exception{
        Cupon cupon= iAdministradorService.buscarCupon(busquedaCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cupon));
    }
    @PutMapping("/responder-PQRS")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> responderPQRS(@Valid @RequestBody ResponderPQRS_DTO responderPQRSDto)throws Exception{
        String cadena= iAdministradorService.responderPQRS(responderPQRSDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @PutMapping("/darEstado-PQRS")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<String>> ponerEstadoA_PQRS(@Valid @RequestBody DarEstadoPQRS darEstadoPQRS)throws Exception{
        String cadena= iAdministradorService.ponerEstadoA_PQRS(darEstadoPQRS);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }

}
