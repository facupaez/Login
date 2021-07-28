package com.solosoftware.Login.servicio;

import com.solosoftware.Login.dao.UnidadDao;
import com.solosoftware.Login.entidad.Unidad;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadServiceImpl implements UnidadService {

    @Autowired
    UnidadDao unidadDao;

    //creamos lista de unidades
    @Override
    public Iterable<Unidad> getAllUnidades() {
        return unidadDao.findAll();
    }

    // metodo para buscar id de la unidad
    @Override
    public Unidad getUnidadById(Long idUnidad) throws Exception {
        return unidadDao.findById(idUnidad).orElseThrow(() -> new Exception("La unidad no existe."));
    }

    //para crear una nueva unidad consultamos al metodo checkUnidadDisponible
    @Override
    public Unidad crearUnidad(Unidad unidad) throws Exception {
        //if (checkUnidadDisponible(unidad)) {
            //guardamos unidad
            unidad = unidadDao.save(unidad);
        //}
        return unidad;
    }

    //metodo eliminar unidad
    @Override
    @PreAuthorize("hasAnyRole('ROLE_SUPER')")
    public void eliminarUnidad(Long idUnidad) throws Exception {
        Unidad unidad = getUnidadById(idUnidad);
        unidadDao.delete(unidad);
    }

    //metodo editar unidad
    @Override
    public Unidad editarUnidad(Unidad fromUnidad) throws Exception {

        Unidad toUnidad = getUnidadById(fromUnidad.getIdUnidad());
        mapUnidad(fromUnidad, toUnidad);

        //editamos unidad en el caso que no exista
        unidadDao.save(toUnidad);
        return toUnidad;
    }

    protected void mapUnidad(Unidad from, Unidad to) {
        to.setDominio(from.getDominio());
        to.setTipoUnidad(from.getTipoUnidad());
        to.setEstadoUnidad(from.getEstadoUnidad());
        to.setDescripcion(from.getDescripcion());
    }

    private boolean checkUnidadDisponible(Unidad unidad) throws Exception {
        Optional<Unidad> unidadCheck = unidadDao.findByDominio(unidad.getDominio());
        if (unidadCheck.isPresent()) {
            throw new Exception("Unidad no disponible");
        }
        return true;
    }
}
