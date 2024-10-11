package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.DTO.EmailDTO;
import co.edu.uniquindio.proyecto.services.interfaces.I_EmailServicie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final I_EmailServicie emailServicie;

    @PutMapping("/enviar-Correo")
    void enviarCorreo(EmailDTO emailDTO) throws Exception{
        emailServicie.enviarCorreo(emailDTO);
    }

}
