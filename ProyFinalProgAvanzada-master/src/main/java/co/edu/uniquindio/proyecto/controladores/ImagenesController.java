package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.DTO.MensajeDTO;
import co.edu.uniquindio.proyecto.services.interfaces.I_ImagenesServicie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
public class ImagenesController {

    private final I_ImagenesServicie iImagenesServicie;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<String>> subir(@RequestParam("imagen") MultipartFile imagen) throws Exception{
        String respuesta = iImagenesServicie.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta));
    }


    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<String>> eliminar(@RequestParam("idImagen") String idImagen)  throws Exception{
        iImagenesServicie.eliminarImagen( idImagen );
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "La imagen fue eliminada correctamente"));
    }


}
