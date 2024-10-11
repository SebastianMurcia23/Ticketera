package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.*;

import java.util.List;

public interface I_ClienteService {



//    Filtrar eventos (por nombre, tipo y ciudad).
//    Gestión de órdenes de compra (crear, cancelar).
//    Gestión de cupones (redimir cupones).
//    Historial de compras (listar sus compras previas).
//    Gestionar la cuenta del cliente (editar y eliminar su cuenta).
//    Carrito de compras persistente.


    String registrarse(RegistrarClienteDTO registrarClienteDTO)throws Exception;
    String editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;
    String borrarCuenta(EliminarCuentaDTO eliminarCuentaDTO)throws Exception;


    List<InfoPrincipalEventDTO> filtrarEvento(FiltrarEvento_NombreTipoCiudad filtroEventoDTO)throws Exception;

    String transferirTicket(TransferirTicketDTO transferirTicketDTO)throws Exception;
    String aceptarTransferencia(RespuestaTransferenciaDTO respuestaTransferenciaDTO)throws Exception;
    String rechazarTransferencia(RespuestaTransferenciaDTO respuestaTransferenciaDTO)throws Exception;




    String redimirCupon(RedimirCuponDTO redimirCuponDTO)throws Exception;

    String quitarCupon(String identificacionClient)throws Exception;


    String anadirACarrito(ItemCompraDTO itemCompraDTO)throws Exception;
    String quitarDeCarrito(SeleccionItemsCompraDTO seleccionItemsCompraDTO)throws Exception;
    String vaciarCarrito(String identificacionCliente)throws Exception;

    String crearPQRS(CrearPQRS_DTO crearPQRS_DTO)throws Exception;
    String cancelarPQRS(CancelarPQRS_DTO cancelarPQRS_dto)throws Exception;
}
