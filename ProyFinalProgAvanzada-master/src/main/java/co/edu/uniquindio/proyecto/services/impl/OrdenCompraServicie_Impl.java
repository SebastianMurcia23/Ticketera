package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.CrearCuponDTO;
import co.edu.uniquindio.proyecto.DTO.EmailDTO;
import co.edu.uniquindio.proyecto.DTO.InfoPrincipalOrdenCompraDTO;
import co.edu.uniquindio.proyecto.DTO.RealizarPagoDTO;
import co.edu.uniquindio.proyecto.Repositories.*;
import co.edu.uniquindio.proyecto.model.*;
import co.edu.uniquindio.proyecto.model.enums.*;
import co.edu.uniquindio.proyecto.services.interfaces.I_ClienteService;
import co.edu.uniquindio.proyecto.services.interfaces.I_CuponService;
import co.edu.uniquindio.proyecto.services.interfaces.I_EmailServicie;
import co.edu.uniquindio.proyecto.services.interfaces.I_OrdenDeServicioService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional
public class OrdenCompraServicie_Impl implements I_OrdenDeServicioService {

    private final EventoRepo eventoServicio;
    private final OrdenRepo ordenRepo;
    private final PagoRepo pagoRepo;
    private final ClienteRepo clienteRepo;
    private final EventoRepo eventoRepo;
    private final I_ClienteService i_clienteService;
    private final CuponRepo cuponRepo;
    private final I_CuponService iCuponService;
    private final I_EmailServicie emailServicie;


    public OrdenCompraServicie_Impl(EventoRepo eventoServicio, OrdenRepo ordenRepo, PagoRepo pagoRepo, ClienteRepo clienteRepo, EventoRepo eventoRepo, I_ClienteService iClienteService, CuponRepo cuponRepo, I_CuponService iCuponService, I_EmailServicie emailServicie) {
        this.eventoServicio = eventoServicio;
        this.ordenRepo = ordenRepo;
        this.pagoRepo = pagoRepo;
        this.clienteRepo = clienteRepo;
        this.eventoRepo = eventoRepo;
        i_clienteService = iClienteService;
        this.cuponRepo = cuponRepo;
        this.iCuponService = iCuponService;
        this.emailServicie = emailServicie;
    }

    /**
     * GENERAR ORDEN DE COMPRA
     * @param idCliente
     * @return
     * @throws Exception
     */
    @Override
    public String generarOrdenCompra(String idCliente) throws Exception {

        Cliente cliente=validarSiExisteClienteIdentificacion(idCliente);
        if (cliente.getCarritoDeCompras()!=null){
            if(!validarSiItemsDisponibles(cliente.getCarritoDeCompras().getListItemsCompra())){
                throw new Exception("Error valide nuevamente los items cuenten con disponibilidad");
            }

            OrdenDeCompra nuevaOrden=new OrdenDeCompra();
            nuevaOrden.setIdCliente(idCliente);
            nuevaOrden.setEstadoCompra(EstadoCompra.EN_PROCESO);
            nuevaOrden.setFechaCreacion(LocalDateTime.now());
            nuevaOrden.setItems(cliente.getCarritoDeCompras().getListItemsCompra());
            nuevaOrden.setIdPago("");
            nuevaOrden.setTotal(cliente.getCarritoDeCompras().getTotal());
            nuevaOrden.setIdCupon(cliente.getCarritoDeCompras().getCupon());
            nuevaOrden.setCodigoPasarela("");

            OrdenDeCompra guardada=ordenRepo.save(nuevaOrden);
            if(cliente.getMisOrdenesCompra()==null){
                List<String>listOrdenes=new ArrayList<>();
                cliente.setMisOrdenesCompra(listOrdenes);
            }
            cliente.getMisOrdenesCompra().add(guardada.getCodigo());
            clienteRepo.save(cliente);

            i_clienteService.vaciarCarrito(idCliente);

            return guardada.getCodigo();
        }




        return "";
    }

    @Override
    public List<String> cancelarOrdenes_Compra(String idCliente, List<String> codsOrdenes) throws Exception {
        List<String>ocCanceladas=new ArrayList<>();
        for(String codOc:codsOrdenes){
            if(!cancelarOrdenCompra(idCliente,codOc).equalsIgnoreCase("")){
                ocCanceladas.add(codOc);
            }else {
                throw new Exception("No se ha podido cancelar la orden: "+codOc);
            }
        }
        return ocCanceladas;
    }
    @Override
    public String cancelarOrdenCompra(String idCliente,String idOrdenDeCompra) throws Exception {
        Cliente client=validarSiExisteClienteIdentificacion(idCliente);
        OrdenDeCompra oc=obtenerOrden(idOrdenDeCompra);

        if(validarClienteDeOC(client.getIdentificacion(),oc.getIdCliente())){
            if(oc.getEstadoCompra()==EstadoCompra.EN_PROCESO){
                oc.setEstadoCompra(EstadoCompra.CANCELADA);
                ordenRepo.save(oc);
                return "Orden de compra CANCELADA.";
            }else{
                throw new Exception("No se puede cancelar una Orden de compra que no esté en proceso.");
            }
        }else{
            throw new Exception("No se puede cancelar la orden de compra si no eres el cliente que la generó.");
        }

    }

    private boolean validarClienteDeOC(String codigo, String idCliente) {
        return codigo.equalsIgnoreCase(idCliente);
    }


    /**
     * validarSiItemsDisponibles
     * validar Si items estan disponibles para generar orden de compra
     * @param listItemsCompra
     * @return
     * @throws Exception
     */
    private boolean validarSiItemsDisponibles(List<ItemCompra> listItemsCompra) throws Exception {
        boolean bandera=true;
        for (ItemCompra item: listItemsCompra){
            bandera= true;
            if(item!=null){
                Evento encontrado=obtenerEvento(item);
                if(validarLocalidad(encontrado,item)){
                    if(validarAsientosDisponibles(encontrado,item)){
                        if(LocalDateTime.now().isBefore(encontrado.getFechaEvento())){
                            bandera= true;
                        }else {throw new Exception("No puedes comprar este item:"+item.getCodigo()+"; El evento: "+encontrado.getNombre()+"Ya Finalizó");}
                    }else {throw new Exception("Evento: "+encontrado.getNombre()+"El asiento no esta disponible o no existe Item:"+item.getCodigo());}
                }else {throw new Exception("No se ha encontrado la localidad Item:"+item.getCodigo());}
            }else {throw new Exception("Ha ocurrido algo con el Item-es null Item:");}
        }
        if (bandera){
            return true;
        }

    return false;
    }

    /**
     *
     * @param encontrado
     * @param item
     * @return
     */
    private boolean validarAsientosDisponibles(Evento encontrado, ItemCompra item) {

        for (Localidad localidad:encontrado.getLocalidades()){
            if(localidad!=null){
                if(localidad.getCodigo().equalsIgnoreCase(item.getLocalidad())){
                    if(localidad.validarDisponibilidadAsientos(item.getIdAsientosLocalidad())){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * validarLocalidad
     * Descripción: Esta función retorna verdadero si encuentra la localidad dada por
     * el objeto parametro de tipo ItemCompra "item" en el objeto parametro EVENTO "encontrado"
     * @param encontrado
     * @param item
     * @return
     */
    private boolean validarLocalidad(Evento encontrado, ItemCompra item) {

        for (Localidad localidad:encontrado.getLocalidades()){
            if(localidad!=null){
                if(localidad.getCodigo().equalsIgnoreCase(item.getLocalidad())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * obtenerEvento
     * Esta funcion obtiene un evento por medio del codigo contenido en un objeto
     * ItemCompra para retornarlo(El evento).
     * @param item
     * @return
     * @throws Exception
     */
    private Evento obtenerEvento(ItemCompra item) throws Exception {
        Optional<Evento>optionalEvento=eventoRepo.findById(item.getCodigoEvento());
        if(!optionalEvento.isPresent()){
            throw new Exception("No se encontró evento del ITEM:"+item.getCodigo());
        }
        return optionalEvento.get();
    }



    @Override
    public OrdenDeCompra obtenerOrdenCompraCliente(String idCliente, String idOrden) throws Exception {
        return null;
    }

    @Override
    public List<InfoPrincipalOrdenCompraDTO> listarMisOrdenes(String idCliente) throws Exception {
        Optional<Cliente>optionalCliente=clienteRepo.findById(idCliente);
        if (!optionalCliente.isPresent()){
            throw new Exception("No se ha encontrado el cliente.");
        }
        Cliente client=optionalCliente.get();
        List<OrdenDeCompra>listaOC=new ArrayList<>();
        for (String codOC:client.getMisOrdenesCompra()){
            OrdenDeCompra encontrada=obtenerOrden(codOC);
        listaOC.add(encontrada);
        }
        List<InfoPrincipalOrdenCompraDTO>listInfoPrincOC=convertirListOCaLisInfprincOC(listaOC);

        return listInfoPrincOC;
    }

    @Override
    public String agregarCupon(String idOrden, String codigoUsableCupon) throws Exception {
        OrdenDeCompra oc=obtenerOrden(idOrden);

        if (oc.getIdCupon().equalsIgnoreCase("")){

            Optional<Cupon> optionalCupon=cuponRepo.findCuponByCodigoUsable(codigoUsableCupon);
            if (!optionalCupon.isPresent()){
                throw new Exception("Cupon no encontrado");
            }

            Cupon cupon=optionalCupon.get();
            if(cupon.getEstadoCupon() == EstadoCupon.DISPONIBLE){
                oc.setIdCupon(cupon.getCodigo());
                ordenRepo.save(oc);
                oc.setTotal(calcularTotalOC(oc.getCodigo()));
                ordenRepo.save(oc);
                return oc.getCodigo();

            }
        }else{
            throw new Exception("Debe retirar el cupon para agregar otro.");
        }

        return null;
    }

    private double calcularTotalOC(String idOC) throws Exception {
        OrdenDeCompra oc=obtenerOrden(idOC);
        double total=0.0;
        for (ItemCompra item:oc.getItems()){
            total+=item.getCantidad()*item.getPrecioUnitario();
        }
        if(!oc.getIdCupon().equalsIgnoreCase("")){
            Optional<Cupon> optionalCupon=cuponRepo.findById(oc.getIdCupon());
            if (!optionalCupon.isPresent()){
                throw new Exception("Cupon no encontrado");
            }

            Cupon cupon=optionalCupon.get();

            total= total-(total * cupon.getPorcentajeCupon());
        }


        return total;
    }

    @Override
    public String quitarCuponOrden(String idOrden) throws Exception {
        OrdenDeCompra oc=obtenerOrden(idOrden);
        oc.setIdCupon("");
        oc.setTotal(calcularTotalOC(oc.getCodigo()));
        ordenRepo.save(oc);
        return oc.getCodigo();
    }

    private List<InfoPrincipalOrdenCompraDTO> convertirListOCaLisInfprincOC(List<OrdenDeCompra> listaOC) {
        List<InfoPrincipalOrdenCompraDTO> listIPOC=new ArrayList<>();
        for (OrdenDeCompra oc:listaOC){
        if (oc!=null){
            InfoPrincipalOrdenCompraDTO nuevo= new InfoPrincipalOrdenCompraDTO(
              oc.getCodigo(),
              oc.getEstadoCompra(),
              oc.getFechaCreacion(),
              oc.getTotal()

            );
            listIPOC.add(nuevo);
        }
    }
    return listIPOC;
    }

    @Override
    public Preference realizarPago(String idOrden) throws Exception {
        // Obtener la orden guardada en la base de datos y los ítems de la orden
        OrdenDeCompra ordenGuardada = obtenerOrden(idOrden);
        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();


        // Recorrer los items de la orden y crea los ítems de la pasarela
        for(ItemCompra item : ordenGuardada.getItems()){

            // Obtener el evento y la localidad del ítem
            Optional<Evento> evento = eventoServicio.findEventoByCodigoAndEstadoEvento(item.getCodigoEvento(), EstadoEvento.ACTIVO);
            Evento encontrado=evento.get();
            if (encontrado==null){
                throw new Exception(                                                                      "Evento no encontrado");
            }

            Localidad localidad = encontrado.obtenerLocalidad(item.getLocalidad());
            double precio=item.getPrecioUnitario();
            if(!ordenGuardada.getIdCupon().equalsIgnoreCase("")){
                Optional<Cupon>optionalCupon=cuponRepo.findById(ordenGuardada.getIdCupon());
                if (optionalCupon.isEmpty()){
                    throw new Exception("No se encontro Cupon agregado");
                }
                Cupon cupon=optionalCupon.get();
                if (cupon.validarEstadoCupon(cupon.getFechaVencimiento())){
                    cupon.setEstadoCupon(EstadoCupon.INACTIVO);
                    cuponRepo.save(cupon);
                    throw new Exception("El cupon esta Vencido");
                }
                if (cupon.validarEstadoCupon(cupon.getFechaVencimiento())){
                    cupon.setEstadoCupon(EstadoCupon.INACTIVO);
                    cuponRepo.save(cupon);
                    throw new Exception("El cupon esta Vencido");
                }
                 precio = item.getPrecioUnitario()-(item.getPrecioUnitario()*cupon.getPorcentajeCupon());

            }

            // Crear el item de la pasarela
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(encontrado.getCodigo())
                            .title(encontrado.getNombre())
                            .pictureUrl(encontrado.getPoster())
                            .categoryId(encontrado.getTipoEvento().name())
                            .quantity(item.getCantidad())
                            .currencyId("COP")
                            .unitPrice(BigDecimal.valueOf(precio))
                            .build();


            itemsPasarela.add(itemRequest);
        }


        // Configurar las credenciales de MercadoPago
        MercadoPagoConfig.setAccessToken("TEST-5333083875849056-101003-d9854d72205ca7c793e7e65ef78e49df-253618559");


        // Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();


        // Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getCodigo()))
                .notificationUrl("URL NOTIFICACION")
                .build();


        // Crear la preferencia en la pasarela de MercadoPago
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        // Guardar el código de la pasarela en la orden
        ordenGuardada.setCodigoPasarela( preference.getId() );
        ordenRepo.save(ordenGuardada);



        //CREACIÓN DE CUPON 10%
        Optional<Cliente> optionalCliente=clienteRepo.findById(ordenGuardada.getIdCliente());
        if(!optionalCliente.isPresent()){
            throw new Exception("Cliente no encontrado");
        }
        Cliente clientVenta=optionalCliente.get();

        if(clientVenta.isPrimerCompra()==true){
            String codCupon= crearCuponDeBienvenida(clientVenta.getIdentificacion());

            EmailDTO emailDTO =new EmailDTO(
                    "GRACIAS por tu primer compra "+clientVenta.getNombreCompleto()+" en Tiquets.com",
                    clientVenta.getEmail(),
                    "Con el siguiente codigo: "+ codCupon +" puedes obtener un descuento del 10% "

            );
            emailServicie.enviarCorreo(emailDTO);
            clientVenta.setPrimerCompra(false);
            clienteRepo.save(clientVenta);
        }


        return preference;

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
                0.10,
                "Cupon de Primera compra"
        );

        return iCuponService.crearCupon(crearCuponDTO);
    }
    private OrdenDeCompra obtenerOrden(String idOrden) throws Exception {
        Optional<OrdenDeCompra>optionalOrdenDeCompra=ordenRepo.findById(idOrden);
        if (!optionalOrdenDeCompra.isPresent()){
            throw new Exception("Orden no encontrada.");
        }

        return optionalOrdenDeCompra.get();
    }

    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {
        try {

            // Obtener el tipo de notificación
            Object tipo = request.get("type");


            // Si la notificación es de un pago entonces obtener el pago y la orden asociada
            if ("payment".equals(tipo)) {


                // Capturamos el JSON que viene en el request y lo convertimos a un String
                String input = request.get("data").toString();


                // Extraemos los números de la cadena, es decir, el id del pago
                String idPago = input.replaceAll("\\D+", "");


                // Se crea el cliente de MercadoPago y se obtiene el pago con el id
                PaymentClient client = new PaymentClient();
                Payment payment = client.get( Long.parseLong(idPago) );


                // Obtener el id de la orden asociada al pago que viene en los metadatos
                String idOrden = payment.getMetadata().get("id_orden").toString();


                // Se obtiene la orden guardada en la base de datos y se le asigna el pago
                OrdenDeCompra orden = obtenerOrden(idOrden);
                Pago pago = crearPago(payment);
                pagoRepo.save(pago);
                orden.setIdPago(pago.getCodigo());
                ordenRepo.save(orden);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String realizarPagoTest(RealizarPagoDTO realizarPagoDTO) throws Exception {
        OrdenDeCompra ordenDeCompra=obtenerOrden(realizarPagoDTO.idOrden());

        if(ordenDeCompra.getEstadoCompra()!=EstadoCompra.EN_PROCESO){
            throw new Exception("La orden debe estar en proceso para realizar el pago.");
        }
        //validar disponibilidad de asientos
        if(!validarSiItemsDisponibles(ordenDeCompra.getItems())){
            throw new Exception("Error valide nuevamente los items cuenten con disponibilidad");
        }
        //validar disponibilidad de cupon
        Optional<Cupon>optionalCupon=cuponRepo.findById(ordenDeCompra.getIdCupon());
        if (optionalCupon.isEmpty()){
            throw new Exception("El cupon no se encontró.");
        }else{
            Cupon cupon=optionalCupon.get();
            if (!cupon.validarEstadoCupon(cupon.getFechaVencimiento())){
                Optional<Cliente> optionalCliente =clienteRepo.findClienteByIdentificacion(ordenDeCompra.getIdCliente());
                if (optionalCliente.isEmpty()){
                    throw new Exception("Cliente no encontrado");
                }
                Cliente client=optionalCliente.get();
                if(validarSiCuponUsadoEnCliente(cupon,ordenDeCompra.getIdCliente())){
                    for(Cupon c : client.getMisCupones()){
                        if(c.getCodigo().equalsIgnoreCase(cupon.getCodigo())){
                            if(c.getUsosRealizados()>=c.getUsosPermitidos()){
                                c.setEstadoCupon(EstadoCupon.NO_DISPONIBLE);
                                clienteRepo.save(client);
                                throw new Exception("El cupon Ya alcanzó todos los usos permitidos.Retirelo de la orden");
                            }
                        }
                    }
                }else {
                    client.getMisCupones().add(cupon);
                    clienteRepo.save(client);
                }
            }else{
                cupon.setEstadoCupon(EstadoCupon.NO_DISPONIBLE);
                cuponRepo.save(cupon);
                throw new Exception("Cupon esta vencido.");

            }
        }

        if(ordenDeCompra.getIdPago().equalsIgnoreCase("")){
            if(ordenDeCompra.getTotal()==realizarPagoDTO.monto()){

                Pago pago = new Pago();
                pago.setTipoPago(realizarPagoDTO.tipoPago());
                pago.setEstadoPago("PENDIENTE_APROBACION");
                pago.setFechaPago(LocalDateTime.now());
                pago.setDetallePago("Pago en linea");
                pago.setCodAutorizacion("");
                pago.setMoneda(realizarPagoDTO.moneda());



                Pago nuevo=pagoRepo.save(pago);
                ordenDeCompra.setIdPago(nuevo.getCodigo());
                ordenRepo.save(ordenDeCompra);
                return nuevo.getCodigo();
            }else{
                throw new Exception("El monto debe ser igual al total de la orden");
            }
        }else{
            throw new Exception("La orden ya cuenta con un pago");
        }

    }

    private boolean validarSiCuponUsadoEnCliente(Cupon cupon, String idCliente) throws Exception {
        Optional<Cliente> optionalCliente =clienteRepo.findClienteByIdentificacion(idCliente);
        if (optionalCliente.isEmpty()){
            throw new Exception("Cliente no encontrado");
        }
        Cliente client=optionalCliente.get();

        if(client.getMisCupones()==null){
            List<Cupon>listCupon=new ArrayList<>();
            client.setMisCupones(listCupon);
            clienteRepo.save(client);

            return false;
        }else{
            for (Cupon cuponClient : client.getMisCupones()){
                if(cuponClient.getCodigo().equalsIgnoreCase(cupon.getCodigo())){
                    return true;
                }
            }
        }


        return false;
    }

    @Override
    public String recibirNotificacionMercadoPagoTest(String idOrden,String estadoPago,String codAUT) throws Exception {
        OrdenDeCompra ordenDeCompra=obtenerOrden(idOrden);

        Optional<Pago>optionalPago=pagoRepo.findById(ordenDeCompra.getIdPago());
        if (optionalPago.isEmpty()){
            throw new Exception("No se encontró el pago");
        }
        Pago pago=optionalPago.get();
        if (pago.getEstadoPago().equalsIgnoreCase("PENDIENTE_APROBACION")){
            if(estadoPago.equalsIgnoreCase("APROBADO")){
                pago.setEstadoPago("APROBADO");
                pago.setCodAutorizacion("APROBADO"+ordenDeCompra.getCodigo());
                ordenDeCompra.setEstadoCompra(EstadoCompra.FINALIZADA_PAGA);

                List<Ticket>listTicketsGenerados=new ArrayList<>();
                listTicketsGenerados=generarTickets(ordenDeCompra.getItems(),ordenDeCompra.getIdCliente());


                Optional<Cliente> optionalCliente=clienteRepo.findClienteByIdentificacion(ordenDeCompra.getIdCliente());
                if (optionalCliente.isEmpty()){
                    throw new Exception("Cliente no encontrado");
                }
                Cliente cliente=optionalCliente.get();
                for (Cupon cupon : cliente.getMisCupones()){
                    if(ordenDeCompra.getIdCupon().equalsIgnoreCase(cupon.getCodigo())){
                        int usos=cupon.getUsosRealizados()+1;
                        cupon.setUsosRealizados(usos);
                    }
                }

                clienteRepo.save(cliente);
                ordenRepo.save(ordenDeCompra);
                pagoRepo.save(pago);

                return "Compra Realizada";
            }
            if(estadoPago.equalsIgnoreCase("RECHAZADO")){
                pago.setEstadoPago("RECHAZADO");
                ordenDeCompra.setEstadoCompra(EstadoCompra.FINALIZADA_NO_PAGA);
            }



            Pago realizado=pagoRepo.save(pago);

            return realizado.getCodigo();
        }else{
            throw new Exception("El pago ya tiene un estado.Favor realizar de nuevo la orden.");
        }




    }

    private List<Ticket> generarTickets(List<ItemCompra> listItems,String idClient) throws Exception {
        Optional<Cliente> optionalCliente=clienteRepo.findClienteByIdentificacion(idClient);
        if (optionalCliente.isEmpty()){
            throw new Exception("Cliente no encontrado");
        }
        Cliente cliente=optionalCliente.get();
        if(cliente.getMisTickets()==null){
            List<Ticket>listTicketClient=new ArrayList<>();
            cliente.setMisTickets(listTicketClient);
            clienteRepo.save(cliente);
        }
        List<Ticket>listaTicketsGenerados=new ArrayList<>();
        for (ItemCompra item:listItems){
            Evento evento=obtenerEvento(item);
            for (Localidad localidad : evento.getLocalidades()){
                if(localidad.getCodigo().equalsIgnoreCase(item.getLocalidad())){
                    for(String idsAsi : item.getIdAsientosLocalidad()){
                       for(Asiento a:localidad.getAsientosLocalidad()){
                           if(idsAsi.equalsIgnoreCase(a.getIdAsiento())){
                               //generar Ticket
                               Ticket ticket=new Ticket();
                               ticket.setCodigo(evento.getCodigo()+"-"+localidad.getCodigo()+"-"+idsAsi);
                               ticket.setAsiento(idsAsi);
                               ticket.setCodigoEvento(evento.getCodigo());
                               ticket.setLocalidad(localidad.getCodigo());
                               ticket.setFechaHora(evento.getFechaEvento());

                               //generar QR
                               ticket.setQr("QR-");
                               listaTicketsGenerados.add(ticket);
                               cliente.getMisTickets().add(ticket);

                               clienteRepo.save(cliente);

                               //ocupar Asiento
                               a.setDisponible(false);
                               a.setUsuarioCompra(cliente.getIdentificacion());
                               a.setIdTicket(ticket.getCodigo());

                                Evento eventEdit=eventoRepo.save(evento);

                           }
                       }

                    }
                }
            }
        }

        return listaTicketsGenerados;
    }



    private Pago crearPago(Payment payment) {

        Pago pago = new Pago();
        pago.setCodigo(payment.getId().toString());
        pago.setFechaPago(payment.getDateCreated().toLocalDateTime() );
        pago.setEstadoPago(payment.getStatus());
        pago.setDetallePago(payment.getStatusDetail());
        pago.setTipoPago(payment.getPaymentTypeId());
        pago.setMoneda(payment.getCurrencyId());
        pago.setCodAutorizacion(payment.getAuthorizationCode());
        pago.setValorPago(payment.getTransactionAmount().floatValue());


        return pago;
    }

    private Cliente validarSiExisteClienteIdentificacion(String identificacionCliente) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Cliente> optionalClient = clienteRepo.findClienteByIdentificacionAndCuentaEstadoCuenta(identificacionCliente, EstadoCuenta.ACTIVA);
        if (optionalClient.isEmpty()) {
            throw new Exception("No se encontró el Cliente");
        }
        Cliente cliente = optionalClient.get();
        return cliente;
    }
    private Cliente validarSiExisteClienteID(String idCliente) throws Exception {
        //Buscamos la transferencia que se quiere actualizar
        Optional<Cliente> optionalClient = clienteRepo.findClienteByCodigoAndCuentaEstadoCuenta(idCliente,EstadoCuenta.ACTIVA);
        if (optionalClient.isEmpty()) {
            throw new Exception("No se encontró el Cliente");
        }
        Cliente cliente = optionalClient.get();
        return cliente;
    }
}
