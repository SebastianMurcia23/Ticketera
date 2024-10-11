package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.DTO.InfoPrincipalOrdenCompraDTO;
import co.edu.uniquindio.proyecto.DTO.RealizarPagoDTO;
import co.edu.uniquindio.proyecto.model.OrdenDeCompra;
import co.edu.uniquindio.proyecto.services.interfaces.I_OrdenDeServicioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class I_OrdenDeCompraServiceTest {

    @Autowired
    private I_OrdenDeServicioService iOrdenDeServicioService;

    @Test
    public void generarOrdenCompra(){


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iOrdenDeServicioService.generarOrdenCompra("1005094005");
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void cancelarOrdenCompra(){

    List<String> listOC=new ArrayList<>();
    listOC.add("670771e90062906ce4a5b10f");
        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iOrdenDeServicioService.cancelarOrdenCompra("1005094005","670771e90062906ce4a5b10f");
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void cancelarOrdenes_Compra(){

        List<String> listOC=new ArrayList<>();
        listOC.add("670771e90062906ce4a5b10f");
        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            List<String> id = iOrdenDeServicioService.cancelarOrdenes_Compra("1005094005",listOC);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void listarMisOrdenes(){

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            List<InfoPrincipalOrdenCompraDTO> id = iOrdenDeServicioService.listarMisOrdenes("1005094005");
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void agregarCupon(){
        String idOrden="67080b52b0cab62ccf2e16ab";
        String codigoUsableCupon="S3B4ST14N";

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iOrdenDeServicioService.agregarCupon(idOrden,codigoUsableCupon);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void quitarCupon(){
        String idOrden="670771e90062906ce4a5b10f";


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iOrdenDeServicioService.quitarCuponOrden(idOrden);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void realizarPagoTest(){
        String idOrden="67080b52b0cab62ccf2e16ab";

        RealizarPagoDTO realizarPagoDTO=new RealizarPagoDTO(
                "67080b52b0cab62ccf2e16ab",
                "TARJETA_CREDITO",
                528000.0,
                "COP"

        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iOrdenDeServicioService.realizarPagoTest(realizarPagoDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void recibirNotificacionMercadoPagoTest(){
        String idOrden="67080b52b0cab62ccf2e16ab";
        String estado="RECHAZADA";
        String codAUT="670771e90062906ce4a5b10f";


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iOrdenDeServicioService.recibirNotificacionMercadoPagoTest(idOrden,estado,codAUT);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

}
