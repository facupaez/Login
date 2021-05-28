package com.solosoftware.Login.servicio;

import com.solosoftware.Login.entidad.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> getAllUsers();

    public Usuario crearUsuario(Usuario usuario) throws Exception;

    public Usuario getUsuarioById(Long idUsuario) throws Exception;

    public void eliminarUsuario(Long idUsuario) throws Exception;

    public Usuario editarUsuario(Usuario usuario) throws Exception;
    
}
