package com.solosoftware.Login.servicio;

import com.solosoftware.Login.entidad.Unidad;

public interface UnidadService {

    public Iterable<Unidad> getAllUnidades();

    public Unidad getUnidadById(Long idUnidad) throws Exception;
    
    public Unidad crearUnidad(Unidad unidad) throws Exception;
    
    public void eliminarUnidad(Long idUnidad) throws Exception;

    public Unidad editarUnidad(Unidad unidad) throws Exception;
}
