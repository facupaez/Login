package com.solosoftware.Login.servicio;

import com.solosoftware.Login.entidad.Usuario;

public interface UsuarioService {

    public Iterable<Usuario> getAllUsers();

    public Usuario crearUsuario(Usuario usuario) throws Exception;

    public Usuario getUsuarioById(Long idUsuario) throws Exception;

    public void eliminarUsuario(Long idUsuario) throws Exception;

    public Usuario editarUsuario(Usuario usuario) throws Exception;
    
}
