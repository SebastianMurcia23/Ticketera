package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.Repositories.ClienteRepo;
import co.edu.uniquindio.proyecto.model.Cliente;
import co.edu.uniquindio.proyecto.model.CodigoValidacion;
import co.edu.uniquindio.proyecto.model.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.model.enums.TipoUsuario;
import co.edu.uniquindio.proyecto.services.interfaces.IAutenticacionServicio;
import co.edu.uniquindio.proyecto.services.interfaces.I_EmailServicie;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AutenticacionServicioImpl implements IAutenticacionServicio {
    private final ClienteRepo clienteRepo;

    private final JWTUtils jwtUtils;
    private final I_EmailServicie emailServicie;

    public AutenticacionServicioImpl(ClienteRepo clienteRepo,  JWTUtils jwtUtils, I_EmailServicie emailServicie) {
        this.clienteRepo = clienteRepo;

        this.jwtUtils = jwtUtils;
        this.emailServicie = emailServicie;
    }

    @Override
    public TokenDTO iniciarSesionUsuario(LoginDTO loginDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findClienteByCuentaUsuario(loginDTO.usuario());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El usuario o la contraseña no validos");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();
        if(cliente.getCuenta().getEstadoCuenta()== EstadoCuenta.INACTIVA){
            throw new Exception("La cuenta esta inactiva");
        }

        if( !passwordEncoder.matches(loginDTO.password(), cliente.getCuenta().getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        if (cliente.getTipoUsuario()== TipoUsuario.ADMIN){
            map.put("rol", "ADMIN");
        }else{
            map.put("rol", "CLIENT");
        }
        map.put("nombre", cliente.getNombreCompleto());
        map.put("id", cliente.getCodigo());


        return new TokenDTO( jwtUtils.generarToken(cliente.getEmail(), map) );
    }


    @Override
    public String restablecerContrasena(RestablecerContrasenaDTO restablecerContrasenaDTO) throws Exception {
        Cliente client = obtenerClienteByCorreo(restablecerContrasenaDTO.emailClient());

        //CREAR CORREO CON CODIGO DE VERIFICACIÓN
        if (client!=null){
            CodigoValidacion c = new CodigoValidacion();
            client.setCodigoValidacion(c);
            clienteRepo.save(client);

            EmailDTO mensaje= new EmailDTO("Correo de restablecimiento",
                    restablecerContrasenaDTO.emailClient(),
                    "Su codigo de recuperación por será el siguiente " +
                            "durante 5 minutos: "+c.getCodigo());

            emailServicie.enviarCorreo(mensaje);
            return "Mensaje enviado a:"+client.getEmail();
        }


        return null;
    }

    @Override
    public String validarCodigoRecuperacion(ValidarCodigoDTO validarCodigoDTO) throws Exception {
        Cliente client = obtenerClienteByCorreo(validarCodigoDTO.emailClient());
        if (client.getCuenta().getEstadoCuenta()==EstadoCuenta.INACTIVA){
            throw new Exception("La cuenta no puede estar inactiva");
        }
        if (client!=null){
            if (client.getCodigoValidacion().getCodigo().equalsIgnoreCase(validarCodigoDTO.codVerif())){
                if (validarTiempo(client.getCodigoValidacion().getFechaCreacion())){
                   return client.getCodigo();
                }else{
                    throw new Exception("El tiempo ha expirado. Porfavor generar otro código");
                }
            }else{
                throw new Exception("Codigo incorrecto");
            }
        }


        return null;
    }

    @Override
    public String cambiarContrasena(CambiarContrasenaDTO cambiarContrasenaDTO) throws Exception {
        Optional<Cliente>optionalClient=clienteRepo.findById(cambiarContrasenaDTO.idClient());
        if (optionalClient==null){
            throw new Exception("No se ha encontrado el cliente o esta inactivo.Favor contactar soporte técnico.");
        }
        Cliente client=optionalClient.get();
        if (client.getCuenta().getEstadoCuenta()!=EstadoCuenta.ACTIVA){
            throw new Exception("El usuario debe estar activo para cambiar su contraseña");
        }

        String passwordEncripted=enciptarPassword(cambiarContrasenaDTO.contrasenaNueva());
        client.getCuenta().setPassword(passwordEncripted);
        clienteRepo.save(client);


        return client.getCodigo();
    }
    private String enciptarPassword(String password){
        //encripta la contraseña antes de guardar
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( password );
        return passwordEncriptada;
    }
    public  boolean validarTiempo(LocalDateTime tiempoReferencia) {
        LocalDateTime ahora = LocalDateTime.now(); // Obtiene el tiempo actual
        Duration duracion = Duration.between(tiempoReferencia, ahora); // Calcula la diferencia entre ambos tiempos

        return duracion.toMinutes() <= 5; // Verifica si han pasado 5 minutos o menos
    }
    private Cliente obtenerClienteByCorreo(String s) throws Exception {
        Optional<Cliente> optionalClient=clienteRepo.findClienteByEmail(s);

        if (optionalClient.isEmpty()){
            throw new Exception("No se encontróeste correo");
        }

        return optionalClient.get();
    }


}

