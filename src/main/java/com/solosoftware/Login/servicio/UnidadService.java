package com.solosoftware.Login.servicio;

import com.solosoftware.Login.entidad.Unidad;

public interface UnidadService {

    public Iterable<Unidad> getAllUnidades();

    public Unidad getUnidadById(Long idUnidad) throws Exception;
}
