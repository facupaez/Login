package com.solosoftware.Login.web;

import com.solosoftware.Login.dao.EstadoUnidadDao;
import com.solosoftware.Login.dao.RolDao;
import com.solosoftware.Login.dao.TipoUnidadDao;
import com.solosoftware.Login.entidad.Unidad;
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

    @Autowired
    TipoUnidadDao tipoUnidadDao;

    @Autowired
    EstadoUnidadDao estadoUnidadDao;

    // login.html
    @GetMapping({"/", "/login"})
    public String loadPage() {
        return "login";
    }

    // plantilla.html
    @GetMapping({"/plantilla"})
    public String loadPlantilla() {
        return "plantilla";
    }

    // Recuperar contraseña
    @GetMapping({"/recuperarPass"})
    public String loadRecuperarPass() {
        return "recuperarPass";
    }

    // listaUsuarios.html
    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "listaUsuarios";
    }

    //crearUsuario.html
    @GetMapping("/crearUsuario")
    public String crearUsuario(Model model) {
        model.addAttribute("registroUsuario", new Usuario());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "crearUsuario";
    }

//    //crear usuario .html
//    @PostMapping("/crearUsuario")
//    public String validarUsuario(@Valid @ModelAttribute("registroUsuario") Usuario usuario, BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            // si hay errores devolvemos los datos ingresados
//            model.addAttribute("registroUsuario", usuario);
//            model.addAttribute("rolesLista", rolDao.findAll());
//            return "crearUsuario";
//        } else {
//            try {
//                usuarioService.crearUsuario(usuario);
//            } catch (Exception ex) {
//                model.addAttribute("formErrorMessage", ex.getMessage());
//                model.addAttribute("registroUsuario", usuario);
//                model.addAttribute("rolesLista", rolDao.findAll());
//                return "crearUsuario";
//            }
//        }
//
//        // mostramos la lista de roles
//        model.addAttribute("rolesLista", rolDao.findAll());
//        return "login";
//    }
    //    crear usuario modal
    @PostMapping("/crearUsuarioM")
    public String crearUsuario(@Valid @ModelAttribute("crearUsuarioM") Usuario usuario, BindingResult result, ModelMap model) throws Exception {
//        if (result.hasErrors()) {
//            // si hay errores devolvemos los datos ingresados
//            model.addAttribute("crearUnidad", unidad);
//            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
//            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
//            return "crearUnidad";
//        } else {
        try {
            usuarioService.crearUsuario(usuario);
            // mostramos las listas de unidades, estados y tipos de unidad
            model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
            model.addAttribute("rolesLista", rolDao.findAll());
        } catch (Exception ex) {
            if (ex.getMessage().contains("UNIQUE")) { //aqui validar que la excepcion sea por uniquekey
                model.addAttribute("createErrorMessage", "Este email está en uso.");
            }
            model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
            model.addAttribute("rolesLista", rolDao.findAll());
            return "listaUsuarios";
        }
        //}

        return "listaUsuarios";
    }

    //editar usuario html
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
            return "listaUsuarios";
        }
        // }

        // mostramos la lista de roles/usuarios
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "listaUsuarios";
    }

    //recuperar id usuario
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
            usuarioService.eliminarUsuario(idUsuario);
        } catch (Exception ex) {
            model.addAttribute("deleteErrorMessage", ex.getMessage());
            model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
            model.addAttribute("rolesLista", rolDao.findAll());
            return "listaUsuarios";
        }
        // mostramos la lista de roles/usuarios
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        model.addAttribute("rolesLista", rolDao.findAll());

        return "listaUsuarios";
    }

    //recuperar id unidad
    @GetMapping("/recuperarIdUnidad")
    @ResponseBody
    public Unidad recuperarIdUnidad(Model model, Long idUnidad) throws Exception {

        model.addAttribute("listaUnidades", unidadService.getAllUnidades());
        model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
        model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());

        return unidadService.getUnidadById(idUnidad);
    }

    // listaUnidades.html
    @GetMapping("/listaUnidades")
    public String listaUnidades(Model model) {

        model.addAttribute("listaUnidades", unidadService.getAllUnidades());
        model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
        model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());

        return "listaUnidades";
    }

    //agregar unidad
    @PostMapping("/crearUnidad")
    public String crearUnidad(@Valid @ModelAttribute("crearUnidad") Unidad unidad, BindingResult result, ModelMap model) throws Exception {
//        if (result.hasErrors()) {
//            // si hay errores devolvemos los datos ingresados
//            model.addAttribute("crearUnidad", unidad);
//            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
//            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
//            return "crearUnidad";
//        } else {
        try {
            unidadService.crearUnidad(unidad);
            // mostramos las listas de unidades, estados y tipos de unidad
            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
        } catch (Exception ex) {
            if (ex.getMessage().contains("UNIQUE")) { //aqui validar que la excepcion sea por uniquekey
                model.addAttribute("createErrorMessage", "Esta unidad está en uso.");
            }
            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
            return "listaUnidades";
        }
        //}

        return "listaUnidades";
    }

    //eliminar unidad
    @PostMapping("/eliminarUnidad")
    public String eliminarUnidad(Long idUnidad, Model model) {
        try {
            unidadService.eliminarUnidad(idUnidad);
            // mostramos las listas de unidades, estados y tipos de unidad
            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
        } catch (Exception ex) {
            model.addAttribute("deleteErrorMessage", ex.getMessage());
            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
            return "listaUnidades";
        }

        return "listaUnidades";
    }

    //editar unidad
    @PostMapping("/editarUnidad")
    public String guardarUnidad(Unidad unidad, BindingResult result, ModelMap model) throws Exception {
//        if (result.hasErrors()) {
//            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
//             model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
//             model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
//        } else {
        try {
            unidadService.editarUnidad(unidad);
            // mostramos las listas de unidades, estados y tipos de unidad
            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
        } catch (Exception ex) {
            if (ex.getMessage().contains("UNIQUE")) { //aqui validar que la excepcion sea por uniquekey
                model.addAttribute("editErrorMessage", "Esta unidad está en uso.");
            }
            model.addAttribute("listaUnidades", unidadService.getAllUnidades());
            model.addAttribute("tipoUnidadLista", tipoUnidadDao.findAll());
            model.addAttribute("estadoUnidadLista", estadoUnidadDao.findAll());
            return "listaUnidades";
        }
        // }

        return "listaUnidades";
    }

}
