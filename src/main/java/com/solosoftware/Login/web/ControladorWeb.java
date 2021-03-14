package com.solosoftware.Login.web;

import com.solosoftware.Login.dao.RolDao;
import com.solosoftware.Login.entidad.Usuario;
import com.solosoftware.Login.servicio.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ControladorWeb {
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    RolDao rolDao;

    //load page
    @GetMapping("/")
    public String inicio() {
        return "login";
    }

    //href
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("registroUsuario", new Usuario());
        model.addAttribute("roles", rolDao.findAll());
        return "registro";
    }

    //href
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
