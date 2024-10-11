package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.ActualizarCuponDTO;
import co.edu.uniquindio.proyecto.DTO.BusquedaCuponDTO;
import co.edu.uniquindio.proyecto.DTO.CrearCuponDTO;
import co.edu.uniquindio.proyecto.DTO.EliminarCuponDTO;
import co.edu.uniquindio.proyecto.Repositories.CuponRepo;
import co.edu.uniquindio.proyecto.model.Cupon;
import co.edu.uniquindio.proyecto.model.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.model.enums.TipoCupon;
import co.edu.uniquindio.proyecto.services.interfaces.I_CuponService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CuponService_Impl  implements I_CuponService {

    private final CuponRepo cuponRepo;

    public CuponService_Impl(CuponRepo cuponRepo) {
        this.cuponRepo = cuponRepo;
    }

    @Override
    public String crearCupon(CrearCuponDTO crearCuponDTO) throws Exception {

        Optional<Cupon>optionalCupon=cuponRepo.findCuponByCodigoUsable(crearCuponDTO.codigoUsable());
        if(optionalCupon.isPresent()){
            throw new Exception("El codigo de cupon ya existe");
        }
        if (crearCuponDTO.fechaVencimiento().isBefore(LocalDateTime.now())){
            throw new Exception("La fecha de vencimiento no puede ser anterior a la fecha de creación");
        }
        if (crearCuponDTO.porcentajeCupon()==0.0){
            throw new Exception("No es necesario crear cupondes del 0%");
        }
        Cupon cupon=new Cupon();
        cupon.setCodigoUsable( crearCuponDTO.codigoUsable());
        cupon.setFechaVencimiento(crearCuponDTO.fechaVencimiento());
        cupon.setPorcentajeCupon(crearCuponDTO.porcentajeCupon());
        cupon.setDescripcion(crearCuponDTO.descripcion());
        if (crearCuponDTO.tipoCupon()== TipoCupon.MULTIPLE){
            cupon.setUsosPermitidos(crearCuponDTO.usosPermitidos());
        }else{
            cupon.setUsosPermitidos(1);
        }
        cupon.setUsosRealizados(0);
        cupon.setEstadoCupon(EstadoCupon.DISPONIBLE);

        Cupon cuponNew=cuponRepo.save(cupon);
        if (cuponNew==null){
            throw new Exception("Ha ocurrido algo.No se puede crear el cupon");
        }
        return cupon.getCodigoUsable();
    }

    @Override
    public String eliminarCupon(EliminarCuponDTO eliminarCuponDTO) throws Exception {
       Cupon cupon= obtenerCupon(eliminarCuponDTO.idCupon());
        cupon.setEstadoCupon(EstadoCupon.INACTIVO);
        cuponRepo.save(cupon);
        return cupon.getCodigo();
    }

    private Cupon obtenerCupon(String s) throws Exception {
        Optional<Cupon>optionalCupon=cuponRepo.findCuponByCodigo(s);
        if (optionalCupon==null){
            throw new Exception("No se encontró cupón.");
        }
        return  optionalCupon.get();
    }

    @Override
    public String actualizarCupon(ActualizarCuponDTO cupon) throws Exception {
        Cupon cuponEncontrado= obtenerCupon(cupon.idCodCupon());
        cuponEncontrado.setFechaVencimiento(cupon.fechaVencimiento());
        cuponEncontrado.setUsosPermitidos(cupon.usosPermitidos());
        cuponEncontrado.setPorcentajeCupon(cupon.porcentajeCupon());
        cuponEncontrado.setDescripcion(cupon.descripcion());
        cuponEncontrado.setEstadoCupon(cupon.estadoCupon());
        cuponRepo.save(cuponEncontrado);
        return cuponEncontrado.getCodigo();
    }

    @Override
    public List<Cupon> listarCupones() throws Exception {
        List<Cupon> optionalCupons=cuponRepo.findAll();
    return optionalCupons;
    }

    @Override
    public Cupon buscarCupon(BusquedaCuponDTO busquedaCuponDTO) throws Exception {
    Optional<Cupon> optionalCupons=cuponRepo.findCuponByCodigoUsable(busquedaCuponDTO.codUsable());
    Cupon encontrados=optionalCupons.get();
    return encontrados;
    }


}
