package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.DTO.EmailDTO;

public interface I_EmailServicie {
    void enviarCorreo(EmailDTO emailDTO) throws Exception;
}
