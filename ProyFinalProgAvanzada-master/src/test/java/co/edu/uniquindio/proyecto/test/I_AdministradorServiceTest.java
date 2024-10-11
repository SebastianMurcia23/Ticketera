package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.DTO.*;
import co.edu.uniquindio.proyecto.model.Asiento;
import co.edu.uniquindio.proyecto.model.Localidad;
import co.edu.uniquindio.proyecto.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyecto.model.enums.TipoEvento;
import co.edu.uniquindio.proyecto.services.interfaces.I_AdministradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class I_AdministradorServiceTest {

    @Autowired
    private I_AdministradorService i_AdministradorService;
    @Test
    public void crearEvento(){
        LocalDateTime fechaActual = LocalDateTime.now();
        // Sumar un mes a la fecha actual
        LocalDateTime fechaConUnMesMas = fechaActual.plusMonths(2);

        Localidad general=new Localidad();

        general.setCapacidadMaxima(40);
        general.setCantAsientosDisponibles(40);
        general.setPrecio(120000.0);
        general.setNombreLocalidad("General");
        List<Asiento>listaAsientoG=new ArrayList<>();
        listaAsientoG=llenarListaAsientos("General",40);
        general.setCodigo(general.getNombreLocalidad());
        general.setAsientosLocalidad(listaAsientoG);

        Localidad platino=new Localidad();
        platino.setCapacidadMaxima(20);
        platino.setCantAsientosDisponibles(20);
        platino.setPrecio(220000.0);
        platino.setNombreLocalidad("Platino");
        List<Asiento>listaAsientoPl=new ArrayList<>();
        listaAsientoPl=llenarListaAsientos("Platino",20);
        platino.setCodigo(platino.getNombreLocalidad());
        platino.setAsientosLocalidad(listaAsientoPl);




        Localidad palcos=new Localidad();
        palcos.setCapacidadMaxima(10);
        palcos.setCantAsientosDisponibles(10);
        palcos.setPrecio(320000.0);
        palcos.setNombreLocalidad("Palcos");
        List<Asiento>listaAsientoPal=new ArrayList<>();
        listaAsientoPal=llenarListaAsientos("Palcos",10);
        palcos.setCodigo(palcos.getNombreLocalidad());
        palcos.setAsientosLocalidad(listaAsientoPal);

        List<Localidad>listLocalidad=new ArrayList<>();
        listLocalidad.add(general);
        listLocalidad.add(platino);
        listLocalidad.add(palcos);

        CrearEventoDTO crearEventoDTO = new CrearEventoDTO(
                "Leyendas vallenatas",
                "La plazita",
                "Barranquilla",
                "Eres Bienvenido a nuestro nuevo evento Leyedas Vallenatas",
                TipoEvento.CONCIERTOS,
                "foto.png",
                "foto2.png",
                "imagen.png",
                fechaConUnMesMas,
                listLocalidad
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_AdministradorService.crearEvento(crearEventoDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    private List<Asiento> llenarListaAsientos(String general,int cant) {
        List<Asiento>entrega=new ArrayList<>();
        for (int i = 1; i < cant+1; i++) {
            Asiento nuevo=new Asiento();
            nuevo.setIdAsiento("COD-"+general+" - "+i);
            nuevo.setNumeroAsiento(general+" - "+i);
            nuevo.setDisponible(true);
            nuevo.setIdTicket("");
            nuevo.setUsuarioCompra("");
            entrega.add(nuevo);
        }

        return entrega;
    }
    @Test
    public void modificarEvento(){
        LocalDateTime fechaActual = LocalDateTime.now();
        // Sumar un mes a la fecha actual
        LocalDateTime fechaConUnMesMas = fechaActual.plusMonths(2);

        Localidad general=new Localidad();
        general.setCapacidadMaxima(43);
        general.setCantAsientosDisponibles(43);
        general.setPrecio(121000.0);
        general.setNombreLocalidad("General-ñame");
        List<Asiento>listaAsientoG=new ArrayList<>();
        listaAsientoG=llenarListaAsientos("General-ñame",40);
        general.setAsientosLocalidad(listaAsientoG);

        Localidad platino=new Localidad();
        platino.setCapacidadMaxima(22);
        platino.setCantAsientosDisponibles(22);
        platino.setPrecio(222000.0);
        platino.setNombreLocalidad("Platino-Aguila");
        List<Asiento>listaAsientoPl=new ArrayList<>();
        listaAsientoPl=llenarListaAsientos("Platino-Aguila",20);
        platino.setAsientosLocalidad(listaAsientoPl);

        Localidad palcos=new Localidad();
        palcos.setCapacidadMaxima(11);
        palcos.setCantAsientosDisponibles(11);
        palcos.setPrecio(323000.0);
        palcos.setNombreLocalidad("Palcos-raton");
        List<Asiento>listaAsientoPal=new ArrayList<>();
        listaAsientoPal=llenarListaAsientos("Palcos-raton",10);
        palcos.setAsientosLocalidad(listaAsientoPal);

        List<Localidad>listLocalidad=new ArrayList<>();
        listLocalidad.add(general);
        listLocalidad.add(platino);
        listLocalidad.add(palcos);

        EditarEventoDTO editarEventoDTO = new EditarEventoDTO(
                "670741cf6b7f59262e21a675",
                "Leyendas bari",
                "La plazita chica",
                "Barranquillaaaa",
                "No Eres Bienvenido a nuestro nuevo evento Leyedas Vallenatas",
                TipoEvento.FAMILIA,
                "foto.png",
                "foto2.png",
                "imagen.png",
                fechaConUnMesMas,
                listLocalidad
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_AdministradorService.modificarEvento(editarEventoDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    @Test
    public void eliminarEvento(){
        EliminarEventoDTO eliminarEventoDTO=new EliminarEventoDTO(
                "670741cf6b7f59262e21a675"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_AdministradorService.eliminarEvento(eliminarEventoDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void listarEventos(){
        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            List<InfoPrincipalEventDTO> id = i_AdministradorService.listarEventos();
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }

    @Test
    public void responderPQRS(){

        ResponderPQRS_DTO responderPQRSDto = new ResponderPQRS_DTO(
                "6706fb3b3aed2b66d36e097c",
                "67070663cef1b2110f10bf20",
                "Primer revision",
                "No se encontraron novedades"
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_AdministradorService.responderPQRS(responderPQRSDto);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
    @Test
    public void darEstadoPQRS(){

        DarEstadoPQRS pqrs= new DarEstadoPQRS(
                "6706fb3b3aed2b66d36e097c",
                "67070663cef1b2110f10bf20",
                EstadoPQRS.SOLUCIONADA
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = i_AdministradorService.ponerEstadoA_PQRS(pqrs);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );
    }
}
