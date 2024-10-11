package co.edu.uniquindio.proyecto.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface I_ImagenesServicie {
    String subirImagen(MultipartFile imagen) throws Exception;

    void eliminarImagen(String idImagen) throws Exception;
}
