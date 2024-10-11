package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.DTO.InfoPrincipalOrdenCompraDTO;
import co.edu.uniquindio.proyecto.DTO.MensajeDTO;
import co.edu.uniquindio.proyecto.DTO.RealizarPagoDTO;
import co.edu.uniquindio.proyecto.model.OrdenDeCompra;
import co.edu.uniquindio.proyecto.services.interfaces.I_OrdenDeServicioService;
import com.mercadopago.resources.preference.Preference;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenDeCompra")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class OrdenDeCompraController {

    private final I_OrdenDeServicioService iOrdenDeServicioService;

    @PostMapping("/generarOC/{idCliente}")
    public ResponseEntity<MensajeDTO<String>> generarOrdenCompra(@PathVariable String idCliente) throws Exception{

        String cadena= iOrdenDeServicioService.generarOrdenCompra(idCliente);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));

    }
    @PutMapping("/cancelar-OsC")
    public ResponseEntity<MensajeDTO<List<String>>> cancelarOrdenes_Compra(@Valid @RequestBody String idCliente, List<String> codsOrdenes) throws Exception{
    List<String> listOC=new ArrayList<>();
        listOC =  iOrdenDeServicioService.cancelarOrdenes_Compra(idCliente,codsOrdenes);
        return ResponseEntity.ok(new MensajeDTO<>(false, listOC));
    }
    @PutMapping("/cancelar-OC")
    public ResponseEntity<MensajeDTO<String>> cancelarOrdenCompra(@Valid @RequestBody String idCliente, String idOrdenDeCompra) throws Exception{
        String cadena= iOrdenDeServicioService.cancelarOrdenCompra(idCliente,idOrdenDeCompra);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @GetMapping("/Obtener-OC-Cliente")
    public ResponseEntity<MensajeDTO<OrdenDeCompra>> obtenerOrdenCompraCliente(@Valid @RequestBody String idCliente, String idOrden) throws Exception{
    OrdenDeCompra oc= new OrdenDeCompra();
            oc=iOrdenDeServicioService.obtenerOrdenCompraCliente(idCliente,idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, oc));
    }
    @GetMapping("/agregarCupon/{idCliente}")
    public ResponseEntity<MensajeDTO<List<InfoPrincipalOrdenCompraDTO>>> listarMisOrdenes(@PathVariable String idCliente) throws Exception{
    List<InfoPrincipalOrdenCompraDTO>nuevaList=new ArrayList<>();
    nuevaList=iOrdenDeServicioService.listarMisOrdenes(idCliente);
        return ResponseEntity.ok(new MensajeDTO<>(false, nuevaList));
    }
    @PostMapping("/agregar-Cupon")
    public ResponseEntity<MensajeDTO<String>> agregarCupon(@Valid @RequestBody String idOrden,String codigoUsableCupon)throws Exception{
        String cadena= iOrdenDeServicioService.agregarCupon(idOrden,codigoUsableCupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @DeleteMapping("/quitar-Cupon-OC/{idOrden}")
    public ResponseEntity<MensajeDTO<String>> quitarCuponOrden(@PathVariable String idOrden)throws Exception{
        String cadena= iOrdenDeServicioService.quitarCuponOrden(idOrden);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @PostMapping("/realizarPagoTest")
    public ResponseEntity<MensajeDTO<String>> realizarPagoTest(@Valid @RequestBody RealizarPagoDTO realizarPagoDTO) throws Exception{
        String cadena= iOrdenDeServicioService.realizarPagoTest(realizarPagoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @GetMapping("/recibirNotificacionPago")
    public ResponseEntity<MensajeDTO<String>> recibirNotificacionMercadoPagoTest(String idOrden,String estadoPago,String codAUT) throws Exception{
        String cadena= iOrdenDeServicioService.recibirNotificacionMercadoPagoTest(idOrden,estadoPago,codAUT);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }

//    //TODO Todos los demás métodos que se van a implementar…
//    Preference realizarPago(String idOrden) throws Exception;
//    void recibirNotificacionMercadoPago(Map<String, Object> request);



}
