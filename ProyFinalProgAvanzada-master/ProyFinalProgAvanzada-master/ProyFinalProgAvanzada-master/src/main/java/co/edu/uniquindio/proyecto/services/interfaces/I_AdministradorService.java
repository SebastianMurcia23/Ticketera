package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.DTO.RegistrarClienteDTO;

public interface I_AdministradorService {

   // Gestionar eventos (crear, modificar, listar, eliminar).
   // Crear y gestionar cupones especiales.
   // Generar reportes a partir de compras hechas por los clientes.
//    Gestionar la cuenta del administrador (editar y eliminar su cuenta).
 //Recuperar contraseña.


    void crearEvento( )throws Exception;
    void modificarEvento( )throws Exception;
    void eliminarEvento( )throws Exception;
    void listarEventos( )throws Exception;
    void recuperarPassword( )throws Exception;
    void crearAdministrador( )throws Exception;
    void modificarAdministrador( )throws Exception;
    void eliminarAdministrador( )throws Exception;
    void listarAdministradores( )throws Exception;
    void eliminarCliente( )throws Exception;
    void buscarCliente( )throws Exception;
    void generarReportePorCorte()throws Exception;



}
