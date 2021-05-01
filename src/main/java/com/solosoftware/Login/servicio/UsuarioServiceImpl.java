package com.solosoftware.Login.servicio;

import com.solosoftware.Login.dao.UsuarioDao;
import com.solosoftware.Login.entidad.Usuario;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    //creamos lista de usuario
    @Override
    public List<Usuario> getAllUsers() {
        return usuarioDao.findAll();
    }

    //checkeamos email existente
    private boolean checkEmailDisponible(Usuario usuario) throws Exception {
        Optional<Usuario> emailCheck = usuarioDao.findByEmail(usuario.getEmail());
        if (emailCheck.isPresent()) {
            throw new Exception("Email no disponible");
        }
        return true;
    }

    //checkeamos coincidencia de contraseñas
    private boolean checkConfirmaPassword(Usuario usuario) throws Exception {
        if (!usuario.getPassword().equals(usuario.getConfirmaPassword())) {
            throw new Exception("Las contraseñas no coinciden");
        }
        return true;
    }

    //para crear un nuevo usuario consultamos a los metodos checkEmailDisponible y checkConfirmaPassword
    @Override
    public Usuario crearUsuario(Usuario usuario) throws Exception {
        if (checkEmailDisponible(usuario) && checkConfirmaPassword(usuario)) {
            usuario = usuarioDao.save(usuario);
        }
        return usuario;
    }
}
