package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.model.Cupon;

import java.util.List;

public interface I_AdministradorService {

   // Gestionar eventos (crear, modificar, listar, eliminar).
   // Crear y gestionar cupones especiales.
   // Generar reportes a partir de compras hechas por los clientes.
//    Gestionar la cuenta del administrador (editar y eliminar su cuenta).
 //Recuperar contraseña.


    String crearEvento(CrearEventoDTO crearEventoDTO)throws Exception;
    String modificarEvento(EditarEventoDTO editarEventoDTO)throws Exception;
    String eliminarEvento(EliminarEventoDTO eliminarEventoDTO)throws Exception;
    List<InfoPrincipalEventDTO> listarEventos()throws Exception;
    void generarReportePorCorte()throws Exception;

    String crearCupon(CrearCuponDTO crearCuponDTO)throws Exception;
    String eliminarCupon(EliminarCuponDTO eliminarCuponDTO)throws Exception;
    String actualizarCupon(ActualizarCuponDTO cupon)throws Exception;
    List<Cupon> listarCupones()throws Exception;
    Cupon buscarCupon(BusquedaCuponDTO busquedaCuponDTO)throws Exception;

    String responderPQRS(ResponderPQRS_DTO responderPQRSDto)throws Exception;

    String ponerEstadoA_PQRS(DarEstadoPQRS darEstadoPQRS)throws Exception;
}
