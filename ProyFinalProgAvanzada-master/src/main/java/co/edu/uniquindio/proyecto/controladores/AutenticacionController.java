package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.services.interfaces.IAutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final IAutenticacionServicio iAutenticacionServicio;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionUsuario(@Valid @RequestBody LoginDTO loginDTO) throws Exception{

        TokenDTO token = iAutenticacionServicio.iniciarSesionUsuario(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, token));

    }
    @PutMapping ("/restablecer-Contrasena")
    public ResponseEntity<MensajeDTO<String>> restablecerContrasena(@Valid @RequestBody RestablecerContrasenaDTO restablecerContrasenaDTO) throws Exception{
        String cadena= iAutenticacionServicio.restablecerContrasena(restablecerContrasenaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
    @PutMapping ("/validar-Contrasena")
    public ResponseEntity<MensajeDTO<String>> validarCodigoRecuperacion(@Valid @RequestBody ValidarCodigoDTO validarCodigoDTO)throws Exception{

        String cadena= iAutenticacionServicio.validarCodigoRecuperacion(validarCodigoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }

    @PutMapping ("/cambiar-Contrasena")
    public ResponseEntity<MensajeDTO<String>> cambiarContrasena(@Valid @RequestBody CambiarContrasenaDTO cambiarContrasenaDTO)throws Exception{

        String cadena= iAutenticacionServicio.cambiarContrasena(cambiarContrasenaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, cadena));
    }
}
