package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.*;

public interface IAutenticacionServicio {
    TokenDTO iniciarSesionUsuario(LoginDTO loginDTO) throws Exception;


    String restablecerContrasena(RestablecerContrasenaDTO restablecerContrasenaDTO) throws Exception;
    String validarCodigoRecuperacion(ValidarCodigoDTO validarCodigoDTO)throws Exception;
    String cambiarContrasena(CambiarContrasenaDTO cambiarContrasenaDTO)throws Exception;
}
