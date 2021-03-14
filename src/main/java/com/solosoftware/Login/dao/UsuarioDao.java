package com.solosoftware.Login.dao;

import com.solosoftware.Login.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    Usuario findByEmail(String email);
}
