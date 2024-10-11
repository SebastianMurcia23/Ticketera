package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.ActualizarCuponDTO;
import co.edu.uniquindio.proyecto.DTO.BusquedaCuponDTO;
import co.edu.uniquindio.proyecto.DTO.CrearCuponDTO;
import co.edu.uniquindio.proyecto.DTO.EliminarCuponDTO;
import co.edu.uniquindio.proyecto.model.Cupon;

import java.util.List;

public interface I_CuponService {

    String crearCupon(CrearCuponDTO crearCuponDTO)throws Exception;
    String eliminarCupon(EliminarCuponDTO eliminarCuponDTO)throws Exception;
    String actualizarCupon(ActualizarCuponDTO cupon)throws Exception;
    List<Cupon> listarCupones()throws Exception;
    Cupon buscarCupon(BusquedaCuponDTO busquedaCuponDTO)throws Exception;

}
