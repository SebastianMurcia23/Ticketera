package co.edu.uniquindio.proyecto.Repositories;

import co.edu.uniquindio.proyecto.model.Cliente;
import co.edu.uniquindio.proyecto.model.enums.EstadoCuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    Optional<Cliente>findClienteByCodigo(String codigo);
    Optional<Cliente>findClienteByCodigoAndCuentaEstadoCuenta(String codigo, EstadoCuenta activa);
    Optional<Cliente> findClienteByIdentificacion(String identificacion);
    Optional<Cliente> findClienteByIdentificacionAndCuentaEstadoCuenta(String identificacion, EstadoCuenta estadoCuenta);
    Optional<Cliente> findClienteByEmail(String email);

    Optional<Cliente> findClienteByCuentaUsuario(String usuario);
    // Consulta derivada para buscar por usuario y contraseña de la cuenta

    Optional<Cliente> findClienteByCuentaUsuarioAndCuentaPassword(String usuario, String contrasena);

    Optional<Cliente> deleteClienteByIdentificacion(String identificacion);
}
