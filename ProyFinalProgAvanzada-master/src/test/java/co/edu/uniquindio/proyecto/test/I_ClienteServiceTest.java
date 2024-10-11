package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.model.enums.TipoSolicitud;
import co.edu.uniquindio.proyecto.services.interfaces.I_ClienteService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class I_ClienteServiceTest {

    @Autowired
    private I_ClienteService i_clienteService;

    @Test
    public void registrarClienteTest(){

        RegistrarClienteDTO nuevoClienteDTO=new RegistrarClienteDTO(
                "123456789",
                "SANTIAGO",
                "juanramos062154@gmail.com",
                "santi123",
                "1q2w3e4r",
                "B. la milagrosa m7#3",
                "31234556"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.registrarse(nuevoClienteDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }

    @Test
    public void editarPerfil(){

        ActualizarClienteDTO actualizarClienteDTO=new ActualizarClienteDTO(
                "6706f234c7289447ad95bf0b",
                "1005094005",
                "Sebastian Ramos A",
                "b la mila",
                "3128028428"
        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.editarPerfil(actualizarClienteDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void borrarCuenta(){

        EliminarCuentaDTO eliminarCuentaDTO=new EliminarCuentaDTO(
                "6706f52fdde8227abb6ce140",
                "1005094005");
        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.borrarCuenta(eliminarCuentaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void vaciarCarrito(){
        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.vaciarCarrito("1005094005");
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void crearPQRS(){

        CrearPQRS_DTO crearPQRSDto=new CrearPQRS_DTO(
                "1005094005",
                TipoSolicitud.QUEJA,
                "Queja",
                "Me quejo"

        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.crearPQRS(crearPQRSDto);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void cancelarPQRS(){

        CancelarPQRS_DTO cancelarPQRS_dto=new CancelarPQRS_DTO(
                "6706fb3b3aed2b66d36e097c"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.cancelarPQRS(cancelarPQRS_dto);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void anadiralCarrito(){
        List<String> listIDS=new ArrayList<>();
        listIDS.add("COD-Platino - 1");
        listIDS.add("COD-Platino - 2");
        listIDS.add("COD-Platino - 3");
//        List<String> listIDS=new ArrayList<>();
//        listIDS.add("COD-General - 4");
//        listIDS.add("COD-General - 5");
//        listIDS.add("COD-General - 6");

        ItemCompraDTO itemCompraDTO = new ItemCompraDTO(
                "1005094005",
                "670755f87c1c4e5a9d2ca233",
                "Platino",
                listIDS



        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.anadirACarrito(itemCompraDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void quitarDelCarrito(){
            List<String> listIds= new ArrayList<>();
            listIds.add("670755f87c1c4e5a9d2ca233-General");

        SeleccionItemsCompraDTO seleccionItemsCompraDTO=new SeleccionItemsCompraDTO(
                "1005094005",listIds
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.quitarDeCarrito(seleccionItemsCompraDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
        }

    @Test
    public void redimirCupon(){

        RedimirCuponDTO redimirCuponDTO=new RedimirCuponDTO(
                "1005094005","S3B4ST14N"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.redimirCupon(redimirCuponDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void quitarCupon(){

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.quitarCupon("1005094005");
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void transferirTicket(){

        TransferirTicketDTO transferirTicketDTO=new TransferirTicketDTO(
                "1005094005",
                "123456789",
                "670755f87c1c4e5a9d2ca233-Platino-COD-Platino - 3"

        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.transferirTicket(transferirTicketDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void aceptarTransferencia(){

        RespuestaTransferenciaDTO respuestaTransferenciaDTO= new RespuestaTransferenciaDTO(
                "6708150a1b143f5bda5c3144"
        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.aceptarTransferencia(respuestaTransferenciaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void rechazarTransferencia(){

        RespuestaTransferenciaDTO respuestaTransferenciaDTO= new RespuestaTransferenciaDTO(
                "6708182187885b311a744e08"
        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_clienteService.rechazarTransferencia(respuestaTransferenciaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
}
