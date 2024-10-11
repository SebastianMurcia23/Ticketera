package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.Repositories.*;
import co.edu.uniquindio.proyecto.Repositories.classRepo.EventoRepositoryCustom;
import co.edu.uniquindio.proyecto.model.*;
import co.edu.uniquindio.proyecto.model.enums.*;
import co.edu.uniquindio.proyecto.services.interfaces.I_ClienteService;
import co.edu.uniquindio.proyecto.services.interfaces.I_CuponService;
import co.edu.uniquindio.proyecto.services.interfaces.I_EmailServicie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional

public class ClienteService_Impl implements I_ClienteService {

    private final ClienteRepo clienteRepo;
    private final TransferenciasRepo transferenciasRepo;
    private final EventoRepo eventoRepo;
    private final CuponRepo cuponRepo;
    private final PQRSRepo pqrsRepo;
    private final I_EmailServicie emailServicie;
    private final EventoRepositoryCustom eventoRepositoryCustom;
    private final I_CuponService iCuponService;

    public ClienteService_Impl(ClienteRepo clienteRepo, TransferenciasRepo transferenciasRepo, EventoRepo eventoRepo, CuponRepo cuponRepo, PQRSRepo pqrsRepo, I_EmailServicie emailServicie, EventoRepositoryCustom eventoRepositoryCustom, I_CuponService iCuponService) {
        this.clienteRepo = clienteRepo;
        this.transferenciasRepo = transferenciasRepo;
        this.eventoRepo = eventoRepo;
        this.cuponRepo = cuponRepo;
        this.pqrsRepo = pqrsRepo;
        this.emailServicie = emailServicie;
        this.eventoRepositoryCustom = eventoRepositoryCustom;
        this.iCuponService = iCuponService;
    }


    @Override
    public String registrarse(RegistrarClienteDTO registrarClienteDTO) throws Exception {
        String mensaje="";
        if( existeIdentificacion(registrarClienteDTO.identificacion()) ){
            mensaje+="La identificacion ya se encuentra registrada "+System.lineSeparator();
        }
        if( existeEmail(registrarClienteDTO.email()) ){
            mensaje+="El correo ya se encuentra registrado"+System.lineSeparator();
        }
        if (existeUsuario(registrarClienteDTO.usuario())){
            mensaje+="El usuario ya está en uso."+System.lineSeparator();
        }
        if(!mensaje.equalsIgnoreCase("")){
            throw new Exception(mensaje);
        }
        Cliente user = new Cliente();
        user.setIdentificacion(registrarClienteDTO.identificacion());
        user.setNombreCompleto(registrarClienteDTO.nombreCompleto());
        Cuenta miCuenta=new Cuenta();
        miCuenta.setUsuario(registrarClienteDTO.usuario());


        String passwordEncripted=enciptarPassword(registrarClienteDTO.password());//Encriptar contraseña
        miCuenta.setPassword(passwordEncripted);

        miCuenta.setEstadoCuenta(EstadoCuenta.POR_ACTIVAR);
        user.setCuenta(miCuenta);
        user.setDireccion(registrarClienteDTO.direccion());
        user.setTelefono(registrarClienteDTO.telefono());
        user.setTipoUsuario(TipoUsuario.CLIENT);
        CodigoValidacion codNewClient=new CodigoValidacion();
        user.setCodigoValidacion(codNewClient);

        List<Transferencia> misTransferencias=new ArrayList<>();
        List<Ticket>misTickets=new ArrayList<>();
        List<Cupon>misCupones=new ArrayList<>();

        CarritoDeCompras carritoDeCompras=new CarritoDeCompras();
        carritoDeCompras.setCupon("");
        user.setCarritoDeCompras(carritoDeCompras);
        user.setEmail(registrarClienteDTO.email());
        user.setMisTransferenciasEnviadas(misTransferencias);
        user.setMisTransferenciasRecibidas(misTransferencias);
        user.setMisTickets(misTickets);
        user.setPrimerCompra(true);
        user.setMisCupones(misCupones);

        Cliente client=clienteRepo.save(user);




        if (client!=null){

            String codCupon= crearCuponDeBienvenida(client.getIdentificacion());

            EmailDTO emailDTO =new EmailDTO(
                    "Bienvenido "+client.getNombreCompleto()+" a Tiquets.com",
                    client.getEmail(),
                    "Con el siguiente codigo: "+ codCupon +" puedes obtener un descuento del 15% "

            );
            emailServicie.enviarCorreo(emailDTO);

            return client.getCodigo();
        }
        return "";

    }

    private String crearCuponDeBienvenida(String identificacion) throws Exception {
        LocalDateTime fechaActual = LocalDateTime.now();

        // Sumar un mes a la fecha actual
        LocalDateTime fechaConUnMesMas = fechaActual.plusMonths(1);

        CrearCuponDTO crearCuponDTO=new CrearCuponDTO(
                identificacion,
                fechaConUnMesMas,
                1,
                TipoCupon.UNICO,
                0.15,
                "Cupon de Bienvenida"
        );

        return iCuponService.crearCupon(crearCuponDTO);
    }

    private String enciptarPassword(String password){
        //encripta la contraseña antes de guardar
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( password );
        return passwordEncriptada;
    }
    private boolean existeIdentificacion(String identificacion) {
        return clienteRepo.findClienteByIdentificacion(identificacion).isPresent();
    }
    private boolean existeUsuario(String usuario) {
        return clienteRepo.findClienteByCuentaUsuario(usuario).isPresent();
    }
    private boolean existeEmail(String email) {
        return clienteRepo.findClienteByEmail(email).isPresent();
    }
    @Override
    public String editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Cliente user = validarSiExisteClienteIdentificacion(actualizarClienteDTO.identificacion());

        user.setNombreCompleto( actualizarClienteDTO.nombreCompleto());
        user.setDireccion(actualizarClienteDTO.direccion());
        user.setTelefono(actualizarClienteDTO.telefono());

        Cliente client=clienteRepo.save(user);
        if (client!=null){
            return "Editado Exitosamente";
        }
        return "";
    }
    @Override
    public String borrarCuenta(EliminarCuentaDTO eliminarCuentaDTO) throws Exception {
        Cliente user = validarSiExisteClienteIdentificacion(eliminarCuentaDTO.identificacion());
        user.getCuenta().setEstadoCuenta(EstadoCuenta.INACTIVA);

        Cliente client=clienteRepo.save(user);
        if (client!=null){
            return "Eliminado correctamente";
        }

        return "";
    }


    private Cliente obtenerClienteByCorreo(String s) throws Exception {
        Optional<Cliente> optionalClient=clienteRepo.findClienteByEmail(s);

        if (optionalClient==null){
            throw new Exception("No se encontróeste correo");
        }

        return optionalClient.get();
    }

    @Override
    public List<InfoPrincipalEventDTO> filtrarEvento(FiltrarEvento_NombreTipoCiudad filtroEventoDTO) throws Exception {
        if(filtroEventoDTO.fechaInicio().isBefore(filtroEventoDTO.fechaFin())){
            throw new Exception("La fecha inicial no puede ser posterior a la final.");
        }

        List<Evento>listEvents=eventoRepositoryCustom.buscarEventos(
                filtroEventoDTO.nombre(),
                filtroEventoDTO.ciudad(),
                filtroEventoDTO.tipoEvento(),
                filtroEventoDTO.fechaInicio(),
                filtroEventoDTO.fechaFin()
        );

        List<InfoPrincipalEventDTO>encontrados=obtenerInfoPrincipalEvents(listEvents);

        return encontrados;
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
    public String transferirTicket(TransferirTicketDTO transferirTicketDTO) throws Exception {
        Cliente clientRemitent =  validarSiExisteClienteIdentificacion(transferirTicketDTO.identificacionRemitente());

        Cliente clientDestiny =   validarSiExisteClienteIdentificacion(transferirTicketDTO.identificacionDestinatario());

        if(clientRemitent.validarSiTieneTicket(transferirTicketDTO.codigoTicket())){
            Transferencia nuevaTransferencia = new Transferencia();

            nuevaTransferencia.setFechaHora(LocalDateTime.now());
            nuevaTransferencia.setRemitente(clientRemitent.getIdentificacion());
            nuevaTransferencia.setDestinatario(clientDestiny.getIdentificacion());
            nuevaTransferencia.setTicket(transferirTicketDTO.codigoTicket());
            nuevaTransferencia.setEstadoTransferencia(EstadoTransferencia.PENDIENTE);

            Transferencia transferencia = transferenciasRepo.save(nuevaTransferencia);

            if(clientRemitent.getMisTransferenciasEnviadas()==null){
                List<Transferencia> nuevo=new ArrayList<>();
                clientRemitent.setMisTransferenciasEnviadas(nuevo);
            }
            if(clientDestiny.getMisTransferenciasRecibidas()==null){
                List<Transferencia> nuevo=new ArrayList<>();
                clientDestiny.setMisTransferenciasRecibidas(nuevo);
            }

            clientRemitent.getMisTransferenciasEnviadas().add(transferencia);
            clientDestiny.getMisTransferenciasRecibidas().add(transferencia);

            clienteRepo.save(clientRemitent);
            clienteRepo.save(clientDestiny);

            return "Se ha enviado exitosamente la transferencia";

        }else{
            throw new Exception("El remitente no posee un ticket con este código.");
        }
    }
    @Override
    public String aceptarTransferencia(RespuestaTransferenciaDTO respuestaTransferenciaDTO) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Transferencia> optionalTransferencia = transferenciasRepo.findByCodigo(respuestaTransferenciaDTO.codigoTransferencia());
        if (optionalTransferencia.isEmpty()) {
            throw new Exception("No se encontró la transferencia");
        }
        Transferencia transferencia = optionalTransferencia.get();

        Cliente clientRemitent = validarSiExisteClienteIdentificacion(transferencia.getRemitente());

        Cliente clientDestiny = validarSiExisteClienteIdentificacion(transferencia.getDestinatario());

        if(clientRemitent.validarSiTieneTicket(transferencia.getTicket())){

            //CAMBIAR EL ESTADO EN LOS CLIENTES

            darEstadoTransferenciaRemi(clientRemitent,transferencia.getCodigo(),EstadoTransferencia.ACEPTADA);
            darEstadoTransferenciaDestin(clientDestiny,transferencia.getCodigo(),EstadoTransferencia.ACEPTADA);


            Ticket ticket=clientRemitent.obtenerTicket(transferencia.getTicket());

            clientDestiny.getMisTickets().add(ticket);
            clientRemitent.getMisTickets().remove(ticket);
            transferencia.setEstadoTransferencia(EstadoTransferencia.ACEPTADA);

            transferenciasRepo.save(transferencia);
            clienteRepo.save(clientRemitent);
            clienteRepo.save(clientDestiny);

            return "Transferencia ACEPTADA.";

        }else{
            throw new Exception("El remitente no posee un ticket con este código.");
        }

    }

    private void darEstadoTransferenciaRemi(Cliente client, String codigo,EstadoTransferencia estadoTransferencia) {

        for (Transferencia transferencia : client.getMisTransferenciasEnviadas()){
            if (transferencia.getCodigo().equalsIgnoreCase(codigo)){
                transferencia.setEstadoTransferencia(estadoTransferencia);
            }

        }
        clienteRepo.save(client);
    }
    private void darEstadoTransferenciaDestin(Cliente client, String codigo,EstadoTransferencia estadoTransferencia) {

        for (Transferencia transferencia : client.getMisTransferenciasRecibidas()){
            if (transferencia.getCodigo().equalsIgnoreCase(codigo)){
                transferencia.setEstadoTransferencia(estadoTransferencia);
            }

        }
        clienteRepo.save(client);
    }

    @Override
    public String rechazarTransferencia(RespuestaTransferenciaDTO respuestaTransferenciaDTO) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Transferencia> optionalTransferencia = transferenciasRepo.findByCodigo(respuestaTransferenciaDTO.codigoTransferencia());
        if (optionalTransferencia.isEmpty()) {
            throw new Exception("No se encontró la transferencia");
        }
        Transferencia transferencia = optionalTransferencia.get();

        //CAMBIAR EL ESTADO EN LOS CLIENTES

        Cliente clientRemitent = validarSiExisteClienteIdentificacion(transferencia.getRemitente());

        Cliente clientDestiny = validarSiExisteClienteIdentificacion(transferencia.getDestinatario());

        darEstadoTransferenciaRemi(clientRemitent,transferencia.getCodigo(),EstadoTransferencia.RECHAZADA);
        darEstadoTransferenciaDestin(clientDestiny,transferencia.getCodigo(),EstadoTransferencia.RECHAZADA);


        transferencia.setEstadoTransferencia(EstadoTransferencia.RECHAZADA);
        transferenciasRepo.save(transferencia);

        return "Transferencia RECHAZADA.";
    }

    @Override
    public String redimirCupon(RedimirCuponDTO redimirCuponDTO) throws Exception {

        Cliente cliente = validarSiExisteClienteIdentificacion(redimirCuponDTO.identificacion());

       Optional<Cupon> optionalCupon = cuponRepo.findCuponByCodigoUsable(redimirCuponDTO.codigoUsable());
        if (optionalCupon.isEmpty()) {
            throw new Exception("Cupon no encontrado");
        }

        Cupon cupon=optionalCupon.get();

        if(cupon.validarEstadoCupon(cupon.getFechaVencimiento())){
            throw new Exception("Cupon vencido.");
        }
        if(cupon.getUsosRealizados() >= cupon.getUsosPermitidos()){
            throw new Exception("Ya se ha utilizado el cupon.");
        }
        cliente.getCarritoDeCompras().setCupon(cupon.getCodigo());
        double totalCarrito=calcularTotalCarrito(cupon.getCodigo(),cliente.getCarritoDeCompras().getListItemsCompra());
        cliente.getCarritoDeCompras().setTotal(totalCarrito);

        Cliente guardado=clienteRepo.save(cliente);
        if (guardado!=null){
            return "Aplicado";
        }

        return "";
    }

    @Override
    public String quitarCupon(String identificacionClient) throws Exception {
        Cliente cliente=validarSiExisteClienteIdentificacion(identificacionClient);
        if(cliente.getCarritoDeCompras()!=null){
            cliente.getCarritoDeCompras().setCupon("");
            //CALCULAR NUEVAMENTE TOTAL
            cliente.getCarritoDeCompras().setTotal(calcularTotalCarrito(
                    cliente.getCarritoDeCompras().getCupon(),
                    cliente.getCarritoDeCompras().getListItemsCompra()
            ));
            clienteRepo.save(cliente);
            return cliente.getCodigo();
        }

        return "";
    }

    private double calcularTotalCarrito(String idCupon, List<ItemCompra> listItemsCompra) {
       Optional<Cupon>optionalCupon=cuponRepo.findById(idCupon);
       Double cupon=0.0;
        if (!optionalCupon.isPresent()){
            cupon=0.0;
        }else{
            Cupon encontrado=optionalCupon.get();
            cupon=encontrado.getPorcentajeCupon();
        }
        double total = 0;
        for (ItemCompra itemCompra : listItemsCompra) {
            if (itemCompra != null) {
                total+= itemCompra.getCantidad() * itemCompra.getPrecioUnitario();
            }
        }
        if (cupon > 0) {
            total= total-(total*cupon);
        }

        return total;
    }
    //falta ver que pasa si agregan a la lista un item que ya existe pero con otros asientos
    @Override
    public String anadirACarrito(ItemCompraDTO itemCompraDTO) throws Exception {

        Cliente cliente = validarSiExisteClienteIdentificacion(itemCompraDTO.identificacionCliente());
        Optional<Evento> optionalEvent = eventoRepo.findEventoByCodigoAndEstadoEvento(itemCompraDTO.codigoEvento(), EstadoEvento.ACTIVO);
        if (optionalEvent.isEmpty()) {
            throw new Exception("No se encontró el evento");
        }
        Evento evento = optionalEvent.get();
        Localidad localidad= evento.obtenerLocalidad(itemCompraDTO.localidad());
        if (localidad==null){
            throw new Exception("No se encontró la localidad");
        }
        if (!localidad.validarDisponibilidadAsientos(itemCompraDTO.idAsientosLocalidad())){
            throw new Exception("Hay asientos que ya estan ocupados.");
        }
        if(cliente.getCarritoDeCompras()==null){
         CarritoDeCompras newCarrito=new CarritoDeCompras();
         newCarrito.setCupon("");
         cliente.setCarritoDeCompras(newCarrito);
        }
        if(validarSiItemYaExisteEnCarrito(cliente.getCarritoDeCompras(),itemCompraDTO)){
            if(validarSiAsientoEsNoDiferente(cliente.getCarritoDeCompras(),itemCompraDTO)){
                throw new Exception("Solo se puede agregar una vez un asiento.");
            }
            cliente.setCarritoDeCompras(agregarPuestosAlItemExistente(cliente.getCarritoDeCompras(),itemCompraDTO));

            double totalCarrito=calcularTotalCarrito(cliente.getCarritoDeCompras().getCupon(),cliente.getCarritoDeCompras().getListItemsCompra());
            cliente.getCarritoDeCompras().setTotal(totalCarrito);

            Cliente user=clienteRepo.save(cliente);
            if (user!=null){
                return "Item agregado con exito al carrito";
            }

        }else {

            ItemCompra nuevoItem = new ItemCompra();
            nuevoItem.setCodigoEvento(itemCompraDTO.codigoEvento());
            nuevoItem.setLocalidad(itemCompraDTO.localidad());
            nuevoItem.setIdAsientosLocalidad(itemCompraDTO.idAsientosLocalidad());
            nuevoItem.setCantidad(itemCompraDTO.idAsientosLocalidad().size());
            nuevoItem.setPrecioUnitario(localidad.getPrecio());
            nuevoItem.setCodigo(itemCompraDTO.codigoEvento()+"-"+itemCompraDTO.localidad());
            cliente.getCarritoDeCompras().getListItemsCompra().add(nuevoItem);
            double totalCarrito = calcularTotalCarrito(cliente.getCarritoDeCompras().getCupon(), cliente.getCarritoDeCompras().getListItemsCompra());
            cliente.getCarritoDeCompras().setTotal(totalCarrito);

            Cliente user = clienteRepo.save(cliente);
            if (user != null) {
                return "Item agregado con exito al carrito";
            }
        }
        return "";
    }

    private CarritoDeCompras agregarPuestosAlItemExistente(CarritoDeCompras carritoDeCompras, ItemCompraDTO itemCompraDTO) {
        for (ItemCompra i:carritoDeCompras.getListItemsCompra()){
            if(i!=null){
                if(i.getCodigoEvento().equalsIgnoreCase(itemCompraDTO.codigoEvento())){
                    if(i.getLocalidad().equalsIgnoreCase(itemCompraDTO.localidad())){

                        for (String id: itemCompraDTO.idAsientosLocalidad()){
                            i.getIdAsientosLocalidad().add(id);
                        }

                    }
                }
            }
            i.setCantidad(i.getIdAsientosLocalidad().size());
        }

        return carritoDeCompras;
    }

    private boolean validarSiAsientoEsNoDiferente(CarritoDeCompras carritoDeCompras, ItemCompraDTO itemCompraDTO) {

        for (ItemCompra i:carritoDeCompras.getListItemsCompra()){
            if(i!=null){
                if(i.getCodigoEvento().equalsIgnoreCase(itemCompraDTO.codigoEvento())){
                    if(i.getLocalidad().equalsIgnoreCase(itemCompraDTO.localidad())){ // encuentra el i rep

                       for (String idAsDTO:itemCompraDTO.idAsientosLocalidad()){
                           if (idAsDTO!=null){
                               for(String idAsCar:i.getIdAsientosLocalidad()){
                                   if (idAsCar!=null){
                                       if (idAsCar.equalsIgnoreCase(idAsDTO)){
                                           return true;
                                       }
                                   }
                               }
                           }
                       }
                       return false;

                    }
                }
            }
        }


        return true;
    }

    private boolean validarSiItemYaExisteEnCarrito(CarritoDeCompras carritoDeCompras, ItemCompraDTO itemCompraDTO) {
    if(carritoDeCompras==null){
        CarritoDeCompras c=new CarritoDeCompras();

    }
    if(!carritoDeCompras.getListItemsCompra().isEmpty()){
        for (ItemCompra i:carritoDeCompras.getListItemsCompra()){
            if(i!=null){
                if(i.getCodigoEvento().equalsIgnoreCase(itemCompraDTO.codigoEvento())){
                    if(i.getLocalidad().equalsIgnoreCase(itemCompraDTO.localidad())){
                        return true;
                    }
                }
            }
        }
    }


        return false;
    }

    @Override
    public String quitarDeCarrito(SeleccionItemsCompraDTO seleccionItemsCompraDTO) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Cliente> optionalClient = clienteRepo.findClienteByIdentificacionAndCuentaEstadoCuenta(seleccionItemsCompraDTO.identificacion(),EstadoCuenta.ACTIVA);
        if (optionalClient.isEmpty()) {
            throw new Exception("No se encontró el cliente del carrito");
        }
        Cliente cliente = optionalClient.get();

        if(cliente.getCarritoDeCompras()!=null){
            if (cliente.getCarritoDeCompras().validarExistenItems(seleccionItemsCompraDTO.idsItemsSeleccionados())){
                for (String idActual : seleccionItemsCompraDTO.idsItemsSeleccionados()){
                    ItemCompra itemCompra=cliente.getCarritoDeCompras().obtenerItemCompra(idActual);
                    cliente.getCarritoDeCompras().getListItemsCompra().remove(itemCompra);
                }

                clienteRepo.save(cliente);

            }else {
                throw new Exception("Algo ha ocurrido, verifica si seleccionaste bien los items");
            }
        }

        return "Error: algo ha ocurrido";
    }
    @Override
    public String vaciarCarrito(String identificacionCliente) throws Exception {

        Optional<Cliente> optionalClient = clienteRepo.findClienteByIdentificacionAndCuentaEstadoCuenta(identificacionCliente,EstadoCuenta.ACTIVA);
        if (optionalClient.isEmpty()) {
            throw new Exception("No se encontró el evento");
        }
        Cliente cliente = optionalClient.get();
        if (cliente.getCarritoDeCompras()==null){
            CarritoDeCompras nuevo=new CarritoDeCompras();
            nuevo.setCupon("");
            List<ItemCompra>listN=new ArrayList<>();
            nuevo.setListItemsCompra(listN);
            nuevo.setTotal(0.0);
            return "Se ha vaciado el carrito correctamente";
        }else{
            List<ItemCompra>listN=new ArrayList<>();
            cliente.getCarritoDeCompras().setListItemsCompra(listN);
            cliente.getCarritoDeCompras().setCupon("");
            cliente.getCarritoDeCompras().setTotal(0.0);
        }

        if (cliente.getCarritoDeCompras().getListItemsCompra().size()==0) {
            clienteRepo.save(cliente);
            return "Se ha vaciado el carrito correctamente";
        }

        return null;
    }

    @Override
    public String crearPQRS(CrearPQRS_DTO crearPQRS_DTO) throws Exception {
        Cliente cliente=validarSiExisteClienteIdentificacion(crearPQRS_DTO.identificacionAutor());

        PQRS documentoPQRS= new PQRS();
        documentoPQRS.setIdCliente(crearPQRS_DTO.identificacionAutor());
        documentoPQRS.setTipoSolicitud(crearPQRS_DTO.tipoSolicitud());
        documentoPQRS.setTitulo(crearPQRS_DTO.titulo());
        documentoPQRS.setMensaje(crearPQRS_DTO.mensaje());
        List<Mensaje>listaNueva= new ArrayList<>();
        documentoPQRS.setRespuestas(listaNueva);
        documentoPQRS.setEstadoPQRS(EstadoPQRS.PENDIENTE);

        PQRS pqrs = pqrsRepo.save(documentoPQRS);

        if (pqrs != null) {
            return "Se ha creado exitosamente la PQRS";
        }


        return "";
    }

    @Override
    public String cancelarPQRS(CancelarPQRS_DTO cancelarPQRS_dto) throws Exception {
        Optional<PQRS> optionalPQRS = pqrsRepo.findByCodigo(cancelarPQRS_dto.codigoPQRS());
        if (optionalPQRS.isEmpty()) {
            throw new Exception("No se encontró la PQRS.");
        }
        PQRS pqrs = optionalPQRS.get();
        if (pqrs.getEstadoPQRS()==EstadoPQRS.SOLUCIONADA){
            throw new Exception("No se puede cancelar una PQRS solucionada.");
        }
        if (pqrs.getEstadoPQRS()==EstadoPQRS.CANCELADA){
            throw new Exception("No se puede cancelar una PQRS cancelada.");
        }
        pqrs.setEstadoPQRS(EstadoPQRS.CANCELADA);

        PQRS pqrsSave = pqrsRepo.save(pqrs);
        if (pqrsSave==null) {
            throw new Exception("Ha ocurrido algo no se encuentra la pqrs editada.");
        }

        return "Se ha cancelado la PQRS correctamente";
    }


    private Cliente validarSiExisteClienteIdentificacion(String identificacionCliente) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Cliente> optionalClient = clienteRepo.findClienteByIdentificacionAndCuentaEstadoCuenta(identificacionCliente,EstadoCuenta.ACTIVA);
        if (!optionalClient.isPresent()) {
            throw new Exception("No se encontró el Cliente");
        }
        Cliente cliente = optionalClient.get();
        return cliente;
    }

    private Cliente validarSiExisteClienteId(String idCliente) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Cliente> optionalClient = clienteRepo.findClienteByIdentificacionAndCuentaEstadoCuenta(idCliente,EstadoCuenta.ACTIVA);
        if (optionalClient.isEmpty()) {
            throw new Exception("No se encontró el Cliente");
        }
        Cliente cliente = optionalClient.get();
        return cliente;
    }
}
