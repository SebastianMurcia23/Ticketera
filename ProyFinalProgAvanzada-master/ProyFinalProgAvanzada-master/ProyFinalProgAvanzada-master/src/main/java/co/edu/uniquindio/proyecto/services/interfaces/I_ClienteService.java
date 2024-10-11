package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.*;

public interface I_ClienteService {



//    Filtrar eventos (por nombre, tipo y ciudad).
//    Gestión de órdenes de compra (crear, cancelar).
//    Gestión de cupones (redimir cupones).
//    Historial de compras (listar sus compras previas).
//    Gestionar la cuenta del cliente (editar y eliminar su cuenta).
//    Carrito de compras persistente.


    void registrarse(RegistrarClienteDTO registrarClienteDTO)throws Exception;
    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO)throws Exception;
    void borrarCuenta(EliminarCuentaDTO eliminarCuentaDTO)throws Exception;



    void iniciarSesion(LoginDTO loginDTO)throws Exception;

    void filtrarEvento(
    //        nombre, tipo y ciudad
    )throws Exception;

    void transferirTicket( )throws Exception;
    void aceptarTransferencia()throws Exception;
    void rechazarTransferencia()throws Exception;

    void generarOrdenDeCompra()throws Exception;
    void cancelarOrdenDeCompra()throws Exception;
    void listarOrdenesDeCompra()throws Exception;

    void redimirCupon()throws Exception;



    void anadirACarrito()throws Exception;
    void quitarDeCarrito( )throws Exception;
    void vaciarCarrito( )throws Exception;
    
}
