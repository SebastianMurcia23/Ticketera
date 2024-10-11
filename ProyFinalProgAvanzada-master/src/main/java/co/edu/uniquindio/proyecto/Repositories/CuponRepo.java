package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Cupon;
import co.edu.uniquindio.proyecto.model.Evento;
import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CuponRepo extends MongoRepository<Cupon, String> {
    Optional<Cupon> findCuponByCodigo(String codigo);
    Optional<List<Cupon>> findCuponsByCodigoUsable(String codigo);

    Optional<Cupon>findCuponByCodigoUsable(String codogoUsable);

    //Page<List<Cupon>> findByNombreContaining(String nombre, Pageable pageable);

}
