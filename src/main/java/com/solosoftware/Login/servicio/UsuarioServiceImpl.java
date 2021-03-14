package com.solosoftware.Login.servicio;

import com.solosoftware.Login.dao.UsuarioDao;
import com.solosoftware.Login.entidad.Usuario;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioDao usuarioDao;
    
    @Override
    public List<Usuario> getAllUsers() {
        return usuarioDao.findAll();
    }

    
}
