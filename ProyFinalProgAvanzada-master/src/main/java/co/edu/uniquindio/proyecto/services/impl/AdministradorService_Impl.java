package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.Repositories.EventoRepo;
import co.edu.uniquindio.proyecto.Repositories.PQRSRepo;
import co.edu.uniquindio.proyecto.model.*;
import co.edu.uniquindio.proyecto.model.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.model.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import co.edu.uniquindio.proyecto.services.interfaces.I_AdministradorService;
import co.edu.uniquindio.proyecto.services.interfaces.I_CuponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class AdministradorService_Impl implements I_AdministradorService {

    private final EventoRepo eventoRepo;
    private final I_CuponService iCuponService;
    private final PQRSRepo pqrsRepo;
    public AdministradorService_Impl(EventoRepo eventoRepo, I_CuponService iCuponService, PQRSRepo pqrsRepo) {
        this.eventoRepo = eventoRepo;
        this.iCuponService = iCuponService;
        this.pqrsRepo = pqrsRepo;
    }

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {

        Evento nuevoEvento=new Evento();

        nuevoEvento.setNombre(crearEventoDTO.nombre());
        nuevoEvento.setDireccion(crearEventoDTO.direccion());
        nuevoEvento.setCiudad(crearEventoDTO.ciudad());
        nuevoEvento.setDescripcion(crearEventoDTO.descripcion());
        nuevoEvento.setTipoEvento(crearEventoDTO.tipoEvento());
        nuevoEvento.setPoster(crearEventoDTO.poster());
        nuevoEvento.setImgBoleta(crearEventoDTO.imgBoleta());
        nuevoEvento.setDistribucionLocalidades(crearEventoDTO.imgDistribucionLocalidades());
        nuevoEvento.setFechaEvento(crearEventoDTO.fechaEvento());

        nuevoEvento.setLocalidades(crearEventoDTO.localidades());
        nuevoEvento.setEstadoEvento(EstadoEvento.ACTIVO);

        Evento guardado=eventoRepo.save(nuevoEvento);
        if (guardado!=null){
            return guardado.getCodigo();
        }

        return "";
    }

    @Override
    public String modificarEvento(EditarEventoDTO editarEventoDTO) throws Exception {
        Evento encontrado= validarSiExisteEvent(editarEventoDTO.codEvent());

        encontrado.setNombre(editarEventoDTO.nombre());
        encontrado.setDireccion(editarEventoDTO.direccion());
        encontrado.setCiudad(editarEventoDTO.ciudad());
        encontrado.setDescripcion(editarEventoDTO.descripcion());
        encontrado.setTipoEvento(editarEventoDTO.tipoEvento());
        encontrado.setPoster(editarEventoDTO.poster());
        encontrado.setFechaEvento(editarEventoDTO.fechaEvento());

        //si ya tiene ventas no puede editar localidades
        if(!validarSiTieneVentas(encontrado)){
            encontrado.setImgBoleta(editarEventoDTO.imgBoleta());
            encontrado.setDistribucionLocalidades(editarEventoDTO.imgDistribucionLocalidades());
            encontrado.setLocalidades(editarEventoDTO.localidades());
        }

        Evento guardado=eventoRepo.save(encontrado);
        if (guardado!=null){
            return guardado.getCodigo();
        }
        return "";
    }

    private boolean validarSiTieneVentas(Evento encontrado) {

        List<Integer>validaciones=new ArrayList<>();

        for (Localidad localidad : encontrado.getLocalidades()){
                if(localidad.getCapacidadMaxima() == localidad.getCantAsientosDisponibles()){

                }else{
                    return true;
                }
        }
        return false;
    }

    @Override
    public String eliminarEvento(EliminarEventoDTO eliminarEventoDTO) throws Exception {
        Evento encontrado= validarSiExisteEvent(eliminarEventoDTO.idEvento());

        encontrado.setEstadoEvento(EstadoEvento.INACTIVO);

        Evento guardado=eventoRepo.save(encontrado);
        if (guardado!=null){
            return guardado.getCodigo();
        }

        return null;
    }

    @Override
    public List<InfoPrincipalEventDTO> listarEventos() throws Exception {
    List<Evento>eventos=eventoRepo.findAll();
    return obtenerInfoPrincipalEvents(eventos);
    }
    private List<InfoPrincipalEventDTO> obtenerInfoPrincipalEvents(List<Evento> listEvents) {
        List<InfoPrincipalEventDTO>encontrados=new ArrayList<>();
        for (Evento event: listEvents){
            if(event!=null){
                InfoPrincipalEventDTO nuevo=new InfoPrincipalEventDTO(
                        event.getCodigo(),
                        event.getPoster(),
                        event.getNombre(),
                        event.getCiudad(),
                        event.getFechaEvento(),
                        event.getDescripcion()
                );
                encontrados.add(nuevo);
            }
        }
        return encontrados;
    }
    @Override
    public void generarReportePorCorte() throws Exception {

    }

    @Override
    public String crearCupon(CrearCuponDTO crearCuponDTO) throws Exception {
    String codigo= iCuponService.crearCupon(crearCuponDTO);
        return codigo;
    }

    @Override
    public String eliminarCupon(EliminarCuponDTO eliminarCuponDTO) throws Exception {
        String codigo= iCuponService.eliminarCupon(eliminarCuponDTO);
        return codigo;
    }

    @Override
    public String actualizarCupon(ActualizarCuponDTO cupon) throws Exception {
        String codigo= iCuponService.actualizarCupon(cupon);
        return codigo;
    }

    @Override
    public List<Cupon> listarCupones() throws Exception {
        return iCuponService.listarCupones();
    }

    @Override
    public Cupon buscarCupon(BusquedaCuponDTO busquedaCuponDTO) throws Exception {
        return iCuponService.buscarCupon(busquedaCuponDTO);
    }

    @Override
    public String responderPQRS(ResponderPQRS_DTO responderPQRSDto) throws Exception {
        Optional<PQRS>optionalPQRS=pqrsRepo.findById(responderPQRSDto.idPQRS());
        if (optionalPQRS.isEmpty()){
            throw new Exception("No se encontró la PQRS.");
        }
        PQRS pqrs=optionalPQRS.get();
        if (pqrs.getEstadoPQRS()== EstadoPQRS.CANCELADA||pqrs.getEstadoPQRS()== EstadoPQRS.SOLUCIONADA){
            throw new Exception("Ya se encuentra finalizada la PQRS.");
        }
        Mensaje mensaje=new Mensaje();
        mensaje.setIdAutor(responderPQRSDto.idAdmin());
        mensaje.setTitulo(responderPQRSDto.titulo());
        mensaje.setMensaje(responderPQRSDto.mensaje());
        pqrs.getRespuestas().add(mensaje);
        pqrs.setIdAdminAtiende(responderPQRSDto.idAdmin());

        pqrsRepo.save(pqrs);

        return pqrs.getCodigo();
    }

    @Override
    public String ponerEstadoA_PQRS(DarEstadoPQRS darEstadoPQRS) throws Exception {
        Optional<PQRS>optionalPQRS=pqrsRepo.findById(darEstadoPQRS.idPQRS());
        if (optionalPQRS.isEmpty()){
            throw new Exception("No se encontró la PQRS.");
        }
        PQRS pqrs=optionalPQRS.get();
        pqrs.setEstadoPQRS(darEstadoPQRS.estadoPQRS());
        pqrs.setIdAdminAtiende(darEstadoPQRS.idAdmin());

        pqrsRepo.save(pqrs);

        return pqrs.getCodigo();
    }

    private Evento validarSiExisteEvent(String codEvent) throws Exception {

        Optional<Evento> optionalEvent = eventoRepo.findEventoByCodigoAndEstadoEvento(codEvent,EstadoEvento.ACTIVO);
        if (optionalEvent.isEmpty()) {
            throw new Exception("No se encontró el evento");
        }
        return optionalEvent.get();
    }
}
