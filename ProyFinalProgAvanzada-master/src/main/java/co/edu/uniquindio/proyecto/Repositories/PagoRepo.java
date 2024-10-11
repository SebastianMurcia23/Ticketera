package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.OrdenDeCompra;
import co.edu.uniquindio.proyecto.model.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepo  extends MongoRepository<Pago, String> {
}
