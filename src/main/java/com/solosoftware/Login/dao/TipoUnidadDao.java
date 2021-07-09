package com.solosoftware.Login.dao;

import com.solosoftware.Login.entidad.TipoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUnidadDao extends JpaRepository<TipoUnidad, Long> {
    
}
