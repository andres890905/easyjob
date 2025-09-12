package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Repository.RolRepository;
import com.easyjob.easyjob.Repository.SucursalRepository;
import com.easyjob.easyjob.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;
    private final SucursalRepository sucursalRepository;

    public RegistroController(UsuarioService usuarioService, RolRepository rolRepository, SucursalRepository sucursalRepository) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("sucursales", sucursalRepository.findAll());
        return "registro";
    }

    @PostMapping
public String registrarUsuario(@ModelAttribute Usuario usuario,
                               @RequestParam Integer rol,
                               @RequestParam Integer sucursal) {

    // Buscar las entidades completas por ID
    usuario.setRol(rolRepository.findById(rol).orElse(null));
    usuario.setSucursal(sucursalRepository.findById(sucursal).orElse(null));

    // Guardar usuario
    usuarioService.registrarUsuario(usuario);
    return "registro_exitoso";
}

}
