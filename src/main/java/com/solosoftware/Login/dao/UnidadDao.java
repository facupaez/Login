package com.solosoftware.Login.dao;

import com.solosoftware.Login.entidad.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadDao extends JpaRepository<Unidad, Long>  {
    
}
