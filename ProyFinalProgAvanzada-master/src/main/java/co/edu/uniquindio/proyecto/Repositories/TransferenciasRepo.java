package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Cliente;
import co.edu.uniquindio.proyecto.model.Transferencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TransferenciasRepo extends MongoRepository<Transferencia, String> {
    Optional<Transferencia> findByCodigo(String codigo);
}
