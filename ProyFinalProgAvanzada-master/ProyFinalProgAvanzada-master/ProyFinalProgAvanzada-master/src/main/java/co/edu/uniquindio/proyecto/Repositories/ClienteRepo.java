package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepo extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByIdentificacion(String identificacion);
    Optional<Cliente> findByEmail(String email);

}
