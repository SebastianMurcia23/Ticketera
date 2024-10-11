package co.edu.uniquindio.proyecto.Repositories.classRepo;

import co.edu.uniquindio.proyecto.Repositories.EventoRepo;
import co.edu.uniquindio.proyecto.model.Evento;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class EventoRepositoryImpl implements EventoRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Override
    public List<Evento> buscarEventos(String nombre, String ciudad, TipoEvento tipoEvento, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (nombre != null && !nombre.isEmpty()) {
            criteria.and("nombre").is(nombre);
        }
        if (ciudad != null && !ciudad.isEmpty()) {
            criteria.and("ciudad").is(ciudad);
        }
        if (tipoEvento != null) {
            criteria.and("tipoEvento").is(tipoEvento);
        }
        if (fechaInicio != null) {
            criteria.and("fechaInicio").gte(fechaInicio);
        }
        if (fechaFinal != null) {
            criteria.and("fechaFinal").lte(fechaFinal);
        }

        query.addCriteria(criteria);
        return mongoTemplate.find(query, Evento.class);
    }
}
