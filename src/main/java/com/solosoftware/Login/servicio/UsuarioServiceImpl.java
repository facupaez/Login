package com.solosoftware.Login.servicio;

import com.solosoftware.Login.dao.UsuarioDao;
import com.solosoftware.Login.entidad.Usuario;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //creamos lista de usuario
    @Override
    public Iterable<Usuario> getAllUsers() {
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

            //encriptamos password
            String encodePassword = bCryptPasswordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encodePassword);

            //guardamos usuario
            usuario = usuarioDao.save(usuario);
        }
        return usuario;
    }

    // metodo para buscar id del usuario
    @Override
    public Usuario getUsuarioById(Long idUsuario) throws Exception {
        return usuarioDao.findById(idUsuario).orElseThrow(() -> new Exception("El usuario no existe."));
    }

    //metodo eliminar usuario
    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void eliminarUsuario(Long idUsuario) throws Exception {

        Usuario usuario = getUsuarioById(idUsuario);
        usuarioDao.delete(usuario);
    }

    //metodo editar usuario
    @Override
    public Usuario editarUsuario(Usuario fromUser) throws Exception {

        Usuario toUser = getUsuarioById(fromUser.getIdUsuario());
        mapUser(fromUser, toUser);

        if (checkEmailDisponible(toUser)) {
            toUser = usuarioDao.save(toUser);
        }
        return toUser;
    }

    //Mapeamos todo menos el password.
    protected void mapUser(Usuario from, Usuario to) {
        to.setNombre(from.getNombre());
        to.setApellido(from.getApellido());
        to.setEmail(from.getEmail());
        to.setRoles(from.getRoles());
    }

}
