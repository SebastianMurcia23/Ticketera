package co.edu.uniquindio.proyecto.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface I_ImagenesServicie {
    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;
}
