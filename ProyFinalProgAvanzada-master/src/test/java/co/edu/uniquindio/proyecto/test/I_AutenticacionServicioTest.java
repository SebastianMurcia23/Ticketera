package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.services.interfaces.IAutenticacionServicio;
import co.edu.uniquindio.proyecto.services.interfaces.I_ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class I_AutenticacionServicioTest {

    @Autowired
    private IAutenticacionServicio iAutenticacionServicio;

    @Test
    public void iniciarSesion(){

        LoginDTO loginDTO=new LoginDTO(
                "sebas0121",
                "1q2w3e4r"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            TokenDTO id = iAutenticacionServicio.iniciarSesionUsuario(loginDTO);

            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    @Test
    public void restablecerContrasena(){

        RestablecerContrasenaDTO restablecerContrasenaDTO=new RestablecerContrasenaDTO(
                "juans.ramosa@uqvirtual.edu.co"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iAutenticacionServicio.restablecerContrasena( restablecerContrasenaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }

    @Test
    public void validarCodigoRecuperacion(){

        ValidarCodigoDTO validarCodigoDTO=new ValidarCodigoDTO(
                "juans.ramosa@uqvirtual.edu.co","100000379593"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iAutenticacionServicio.validarCodigoRecuperacion( validarCodigoDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    @Test
    public void cambiarContrasenaDTO(){

        CambiarContrasenaDTO cambiarContrasenaDTO=new CambiarContrasenaDTO(
               "67070663cef1b2110f10bf20","1q2w3e4r"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = iAutenticacionServicio.cambiarContrasena( cambiarContrasenaDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
}
