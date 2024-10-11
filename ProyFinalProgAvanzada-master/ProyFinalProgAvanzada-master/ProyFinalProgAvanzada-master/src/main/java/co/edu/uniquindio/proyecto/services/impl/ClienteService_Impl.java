package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.ActualizarClienteDTO;
import co.edu.uniquindio.proyecto.DTO.EliminarCuentaDTO;
import co.edu.uniquindio.proyecto.DTO.LoginDTO;
import co.edu.uniquindio.proyecto.DTO.RegistrarClienteDTO;
import co.edu.uniquindio.proyecto.Repositories.ClienteRepo;
import co.edu.uniquindio.proyecto.model.Cliente;
import co.edu.uniquindio.proyecto.services.interfaces.I_ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClienteService_Impl implements I_ClienteService {

    private final ClienteRepo clienteRepo;

    public ClienteService_Impl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }


    @Override
    public void registrarse(RegistrarClienteDTO registrarClienteDTO) throws Exception {
        Cliente user = new Cliente();
        if( existeIdentificacion(registrarClienteDTO.identificacion()) ){
            throw new Exception("La identificacion ya se encuentra registrada");
        }
        if( registrarClienteDTO.nombreCompleto()==null || registrarClienteDTO.nombreCompleto().equalsIgnoreCase("") ){
            throw new Exception("La identificacion ya se encuentra registrada");
        }
        if( existeEmail(registrarClienteDTO.email()) ){
            throw new Exception("El correo ya se encuentra registrado");
        }
        if(registrarClienteDTO.direccion()==null || registrarClienteDTO.direccion().equalsIgnoreCase("")){
            throw new Exception("La dirección no puede estar vacia.");
        }
        if(registrarClienteDTO.telefono()==null || registrarClienteDTO.telefono().equalsIgnoreCase("") ){
            throw new Exception("El telefono no puede estar vacio.");
        }
        if(registrarClienteDTO.usuario()==null || registrarClienteDTO.usuario().equalsIgnoreCase("") ){
            throw new Exception("El usuario no puede estar vacio.");
        }else{
            if (){

            }
        }
    }
    private boolean existeIdentificacion(String identificacion) {
        return clienteRepo.findByIdentificacion(identificacion).isPresent();
    }

    private boolean existeUsuario(String username) {
        return clienteRepo.findByUsername(username).isPresent();
    }

    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }
    @Override
    public void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {

    }

    @Override
    public void borrarCuenta(EliminarCuentaDTO eliminarCuentaDTO) throws Exception {

    }

    @Override
    public void iniciarSesion(LoginDTO loginDTO) throws Exception {

    }

    @Override
    public void filtrarEvento() throws Exception {

    }

    @Override
    public void transferirTicket() throws Exception {

    }

    @Override
    public void aceptarTransferencia() throws Exception {

    }

    @Override
    public void rechazarTransferencia() throws Exception {

    }

    @Override
    public void generarOrdenDeCompra() throws Exception {

    }

    @Override
    public void cancelarOrdenDeCompra() throws Exception {

    }

    @Override
    public void listarOrdenesDeCompra() throws Exception {

    }

    @Override
    public void redimirCupon() throws Exception {

    }

    @Override
    public void anadirACarrito() throws Exception {

    }

    @Override
    public void quitarDeCarrito() throws Exception {

    }

    @Override
    public void vaciarCarrito() throws Exception {

    }
}
