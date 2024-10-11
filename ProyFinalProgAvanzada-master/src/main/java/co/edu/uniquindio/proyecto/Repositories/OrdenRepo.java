package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Cliente;
import co.edu.uniquindio.proyecto.model.OrdenDeCompra;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepo extends MongoRepository<OrdenDeCompra, String> {
}
