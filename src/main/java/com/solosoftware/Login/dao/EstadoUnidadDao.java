package com.solosoftware.Login.dao;

import com.solosoftware.Login.entidad.EstadoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoUnidadDao extends JpaRepository<EstadoUnidad, Long> {

}
