package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Cliente;
import co.edu.uniquindio.proyecto.model.Evento;
import co.edu.uniquindio.proyecto.model.Localidad;
import co.edu.uniquindio.proyecto.model.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface EventoRepo  extends MongoRepository<Evento, String> {
    Optional<Evento> findByCodigo(String codigo);
    Optional<Evento> findEventoByCodigoAndEstadoEvento(String codigo,EstadoEvento estadoEvento);

    @Query("{ 'nombre': { $regex: ?0, $options: 'i' }, 'ciudad': ?1, 'TipoEvento': ?2, 'fechaEvento': { $gte: ?3, $lte: ?4 } }")
    List<Evento>filtrarPorNomCiudadYTipo(String nombre, String Ciudad, TipoEvento tipoEvento);

}