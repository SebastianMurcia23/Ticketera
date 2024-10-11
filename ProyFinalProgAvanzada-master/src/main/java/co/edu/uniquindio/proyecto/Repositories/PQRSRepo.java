package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Evento;
import co.edu.uniquindio.proyecto.model.PQRS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PQRSRepo extends MongoRepository<PQRS, String> {
    Optional<PQRS> findByCodigo(String codigo);
}
