package com.solosoftware.Login.servicio;

import com.solosoftware.Login.dao.UnidadDao;
import com.solosoftware.Login.entidad.Unidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Unidad getUnidadById(Long idUnidad) throws Exception {
        return unidadDao.findById(idUnidad).orElseThrow(() -> new Exception("La unidad no existe."));
    }
}
