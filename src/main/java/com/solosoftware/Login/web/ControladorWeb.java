package com.solosoftware.Login.web;

import com.solosoftware.Login.dao.RolDao;
import com.solosoftware.Login.entidad.Usuario;
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
    RolDao rolDao;

    @GetMapping({"/", "/login"})
    public String loadPage() {
        return "login";
    }

    @GetMapping("/index")
    public String listaUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.getAllUsers());
        return "index";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("registroUsuario", new Usuario());
        model.addAttribute("roles", rolDao.findAll());

        return "registro";
    }

    //metodo validaciones spring
    @PostMapping("/registro")
    public String validarUsuario(@Valid @ModelAttribute("registroUsuario") Usuario usuario, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            // si hay errores devolvemos los datos ingresados
            model.addAttribute("registroUsuario", usuario);
            model.addAttribute("roles", rolDao.findAll());
            return "registro";
        } else {
            try {
                usuarioService.crearUsuario(usuario);
            } catch (Exception ex) {
                model.addAttribute("formErrorMessage", ex.getMessage());
                model.addAttribute("registroUsuario", usuario);
                model.addAttribute("roles", rolDao.findAll());
                return "registro";
            }
        }

        // mostramos la lista de roles
        model.addAttribute("roles", rolDao.findAll());
        return "login";
    }

    // eliminar usuario
    @GetMapping("/eliminarUsuario/{idUsuario}")
    public String eliminarUsuario(Model model, @PathVariable(name = "idUsuario") Long idUsuario) {
        try {
            usuarioService.eliminarUsuario(idUsuario);
        } catch (Exception e) {
            model.addAttribute("listErrorMessage", e.getMessage());
        } 
        //retornamos getmapping index(lista)
        return "redirect:/index";
    }
}
