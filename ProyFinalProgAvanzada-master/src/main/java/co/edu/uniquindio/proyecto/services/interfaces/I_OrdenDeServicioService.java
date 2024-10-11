package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.InfoPrincipalOrdenCompraDTO;
import co.edu.uniquindio.proyecto.DTO.RealizarPagoDTO;
import co.edu.uniquindio.proyecto.model.OrdenDeCompra;
import com.mercadopago.resources.preference.Preference;

import java.util.List;
import java.util.Map;

public interface I_OrdenDeServicioService {


    String generarOrdenCompra(String idCliente) throws Exception;
    List<String> cancelarOrdenes_Compra(String idCliente, List<String> codsOrdenes) throws Exception;
    String cancelarOrdenCompra(String idCliente,String idOrdenDeCompra) throws Exception;
    OrdenDeCompra obtenerOrdenCompraCliente(String idCliente,String idOrden) throws Exception;
    List<InfoPrincipalOrdenCompraDTO> listarMisOrdenes(String idCliente) throws Exception;

    String agregarCupon(String idOrden,String codigoUsableCupon)throws Exception;
    String quitarCuponOrden(String idOrden)throws Exception;
    //TODO Todos los demás métodos que se van a implementar…
    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);

    String realizarPagoTest(RealizarPagoDTO realizarPagoDTO) throws Exception;
    String recibirNotificacionMercadoPagoTest(String idOrden,String estadoPago,String codAUT) throws Exception;
}
