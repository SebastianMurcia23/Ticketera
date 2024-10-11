package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.DTO.ActualizarCuponDTO;
import co.edu.uniquindio.proyecto.DTO.BusquedaCuponDTO;
import co.edu.uniquindio.proyecto.DTO.CrearCuponDTO;
import co.edu.uniquindio.proyecto.DTO.EliminarCuponDTO;
import co.edu.uniquindio.proyecto.model.Cupon;
import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.model.enums.TipoCupon;
import co.edu.uniquindio.proyecto.services.interfaces.I_CuponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class I_CuponServiceTest {

    @Autowired
    private I_CuponService iCuponService;

    @Test
    public void crearCuponTest(){
        LocalDateTime fechaActual = LocalDateTime.now();

        // Sumar un mes a la fecha actual
        LocalDateTime fechaConUnMesMas = fechaActual.plusMonths(1);

        CrearCuponDTO crearCuponDTO = new CrearCuponDTO(
                "P4UL4",
                fechaConUnMesMas,
                1,
                TipoCupon.UNICO,
                0.2,
                "20% de descuento"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iCuponService.crearCupon(crearCuponDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    @Test
    public void actualizarCupon(){
        LocalDateTime fechaActual = LocalDateTime.now();

        // Sumar un mes a la fecha actual
        LocalDateTime fechaConUnMesMas = fechaActual.plusMonths(2);

        ActualizarCuponDTO actualizarCupon = new ActualizarCuponDTO(
              "6706fca5592e6e09306db760",
                fechaConUnMesMas,
                1,
                0.25,
                "CUPON EDITADO ",
                EstadoCupon.NO_DISPONIBLE
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iCuponService.actualizarCupon(actualizarCupon);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }

    @Test
    public void borrarCupon(){
        EliminarCuponDTO eliminarCuponDTO=new EliminarCuponDTO(
                "6706f52fdde8227abb6ce141"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iCuponService.eliminarCupon(eliminarCuponDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    @Test
    public void listCupons(){

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            List<Cupon> id = iCuponService.listarCupones();
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }


    @Test
    public void buscarCupons(){

        BusquedaCuponDTO busquedaCuponDTO=new BusquedaCuponDTO(

                "1005094005"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            Cupon id = iCuponService.buscarCupon( busquedaCuponDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
}
