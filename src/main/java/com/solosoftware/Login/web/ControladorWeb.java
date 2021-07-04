package com.solosoftware.Login.web;

import com.solosoftware.Login.dao.RolDao;
import com.solosoftware.Login.entidad.Usuario;
import com.solosoftware.Login.servicio.UnidadService;
import com.solosoftware.Login.servicio.UsuarioService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ControladorWeb {

    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    UnidadService unidadService;

    @Autowired
    RolDao rolDao;

    // login.html
    @GetMapping({"/", "/login"})
    public String loadPage() {
        return "login";
    }

    // index.html
    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "listaUsuarios";
    }

    // listaUnidades.html
    @GetMapping("/listaUnidades")
    public String listaUnidades(Model model) {
        model.addAttribute("listaUnidades", unidadService.getAllUnidades());

        return "listaUnidades";
    }

    //registro.html
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("registroUsuario", new Usuario());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "registro";
    }

    //agregar usuario
    @PostMapping("/registro")
    public String validarUsuario(@Valid @ModelAttribute("registroUsuario") Usuario usuario, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            // si hay errores devolvemos los datos ingresados
            model.addAttribute("registroUsuario", usuario);
            model.addAttribute("rolesLista", rolDao.findAll());
            return "registro";
        } else {
            try {
                usuarioService.crearUsuario(usuario);
            } catch (Exception ex) {
                model.addAttribute("formErrorMessage", ex.getMessage());
                model.addAttribute("registroUsuario", usuario);
                model.addAttribute("rolesLista", rolDao.findAll());
                return "registro";
            }
        }

        // mostramos la lista de roles
        model.addAttribute("rolesLista", rolDao.findAll());
        return "login";
    }

    //editar usuario
    @PostMapping("/editarUsuario")
    public String guardarUsuario(Usuario usuario, BindingResult result, ModelMap model) throws Exception {
//        if (result.hasErrors()) {
//            model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
//            model.addAttribute("rolesLista", rolDao.findAll());
//        } else {
        try {
            usuarioService.editarUsuario(usuario);
        } catch (Exception ex) {
            if (ex.getMessage().contains("UNIQUE")) { //aqui validar que la excepcion sea por uniquekey
                model.addAttribute("editErrorMessage", "Este email está en uso.");
            }
            model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
            model.addAttribute("rolesLista", rolDao.findAll());
            return "index";
        }
        // }

        // mostramos la lista de roles/usuarios
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "index";
    }

    @GetMapping("/recuperarIdUsuario")
    @ResponseBody
    public Usuario recuperarIdUsuario(Model model, Long idUsuario) throws Exception {

        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return usuarioService.getUsuarioById(idUsuario);
    }

    //eliminar usuario
    @PostMapping("/eliminarUsuario")
    public String eliminarUsuario(Long idUsuario, Model model) {
        try {
            //usuarioService.getUsuarioById(idUsuario);
            usuarioService.eliminarUsuario(idUsuario);
        } catch (Exception ex) {
            model.addAttribute("deleteErrorMessage", ex.getMessage());
            model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
            model.addAttribute("rolesLista", rolDao.findAll());
            return "index";
        }
        // mostramos la lista de roles/usuarios
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "index";
    }
}
