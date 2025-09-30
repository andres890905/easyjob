package com.easyjob.easyjob.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/sobre_nosotros")
    public String SobreNosotros() {
        return "sobre_nosotros"; // la vista login.html
    }
    
}
