package com.solosoftware.Login.web;

import com.solosoftware.Login.entidad.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ControladorWeb {

    //load page
    @GetMapping("/")
    public String inicio() {
        return "login";
    }

    //href
    @GetMapping("/registro.html")
    public String registro() {
        return "registro";
    }

    //href
    @GetMapping("/login.html")
    public String login() {
        return "login";
    }
    
    //registrar usuario
    //@PostMapping("/registro")
}
