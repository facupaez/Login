package com.solosoftware.Login.dao;

import com.solosoftware.Login.entidad.Unidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadDao extends JpaRepository<Unidad, Long> {

    public Optional<Unidad> findByDominio(String dominio);
}
