package co.edu.uniquindio.proyecto.Repositories.classRepo;

import co.edu.uniquindio.proyecto.model.Evento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepositoryCustom {

    List<Evento> buscarEventos(String nombre, String ciudad, TipoEvento tipoEvento, LocalDateTime fechaInicio, LocalDateTime fechaFinal);

}
